package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ICapacityPersistencePort;
import com.pragma.onclass.utils.exceptions.DuplicateTechnologyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapacityUseCaseTest {
    @Mock
    private ICapacityPersistencePort capacityPersistencePort;

    @InjectMocks
    private CapacityUseCase capacityServicePort;


    @InjectMocks
    private CapacityUseCase capacityUseCase;


    @Test
    @DisplayName("Test save capacity")
    void testSaveCapacity() {
        //GIVEN (DADO)
        List<Technology> technologies = Arrays.asList(
                new Technology(1L, "Java", "Programming Language"),
                new Technology(2L, "Python", "Programming Language"),
                new Technology(3L, "Django", "Programming Language")
        );
        Capacity capacity = new Capacity(1L, "Java", "Programming Language", technologies);



        capacityServicePort.saveCapacity(capacity);


        verify(capacityPersistencePort, times(1)).saveCapacity(capacity);


    }

    @Test
    void TestGetCapacityByName() {
        List<Technology> technologies = Arrays.asList(
                new Technology(1L, "Java", "Programming Language"),
                new Technology(2L, "Python", "Programming Language"),
                new Technology(3L, "Django", "Programming Language")
        );
        Capacity capacity = new Capacity(1L, "Java", "Programming Language", technologies);
        given(capacityPersistencePort.getCapacity("Java")).willReturn(capacity);


        Capacity result = capacityServicePort.getCapacity("Java");

        assertEquals(capacity.getName(), result.getName());
        assertEquals(capacity.getTechnologies().size(), result.getTechnologies().size());
        for (int i = 0; i < capacity.getTechnologies().size(); i++) {
            assertEquals(capacity.getTechnologies().get(i).getName(), result.getTechnologies().get(i).getName());
        }
    }

    @Test
    void TestGetAllCapacities() {
        //GIVEN (DADO)
        List<Technology> technologies = Arrays.asList(
                new Technology(1L, "Java", "Programming Language"),
                new Technology(2L, "Python", "Programming Language"),
                new Technology(3L, "Django", "Programming Language")
        );
        List<Capacity> capacities = new ArrayList<>();
        capacities.add(new Capacity(1L, "Java", "Programming Language", technologies));
        capacities.add(new Capacity(2L, "Python", "Programming Language", technologies));
        capacities.add(new Capacity(3L, "Django", "Programming Language", technologies));
        given(capacityPersistencePort.getAllCapacities(0, 3, true, true)).willReturn(capacities);


        List<Capacity> result = capacityServicePort.getAllCapacities(0, 3, true, true);

        assertEquals(capacities.size(), result.size());
        for (int i = 0; i < capacities.size(); i++) {
            assertEquals(capacities.get(i).getName(), result.get(i).getName());
        }
    }

    @Test
     void testSaveCapacityWithDuplicateTechnology() {



        Technology tech1 = new Technology(1L, "Tech1", "some description");
        Technology tech2 = new Technology(1L, "Tech2", "some description");


        List<Technology> technologies = Arrays.asList(tech1, tech2);

        Capacity capacity = new Capacity(1L, "Java", "Programming Language", technologies);


        assertThrows(DuplicateTechnologyException.class, () -> {
            capacityUseCase.saveCapacity(capacity);
        });

        verify(capacityPersistencePort, never()).saveCapacity(capacity);
    }
}