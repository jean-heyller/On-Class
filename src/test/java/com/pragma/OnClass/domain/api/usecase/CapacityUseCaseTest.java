package com.pragma.OnClass.domain.api.usecase;

import com.pragma.OnClass.domain.model.Capacity;
import com.pragma.OnClass.domain.model.Technology;
import com.pragma.OnClass.domain.spi.ICapacityPersistencePort;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CapacityUseCaseTest {
    @Mock
    private ICapacityPersistencePort capacityPersistencePort;

    @InjectMocks
    private CapacityUseCase capacityServicePort;
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


        //WHEN (CUANDO)
        capacityServicePort.saveCapacity(capacity);

        //THEN (ENTONCES)
        verify(capacityPersistencePort, times(1)).saveCapacity(capacity);


    }

    @Test
    void TestGetCapacityByName() {
        //GIVEN (DADO)
        List<Technology> technologies = Arrays.asList(
                new Technology(1L, "Java", "Programming Language"),
                new Technology(2L, "Python", "Programming Language"),
                new Technology(3L, "Django", "Programming Language")
        );
        Capacity capacity = new Capacity(1L, "Java", "Programming Language", technologies);
        given(capacityPersistencePort.getCapacity("Java")).willReturn(capacity);

        //WHEN (CUANDO)
        Capacity result = capacityServicePort.getCapacity("Java");

        //THEN (ENTONCES)
        assertEquals(capacity.getName(), result.getName());
        assertEquals(capacity.getTechnologies().size(), result.getTechnologies().size());
        for (int i = 0; i < capacity.getTechnologies().size(); i++) {
            assertEquals(capacity.getTechnologies().get(i).getName(), result.getTechnologies().get(i).getName());
        }
    }
}