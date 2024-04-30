package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.IBootcampPersistencePort;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class BootcampUseCaseTest {

    @Mock
    private IBootcampPersistencePort bootCampPersistencePort;

    @InjectMocks
    private BootcampUseCase bootCampServicePort;

    @Test
    void testSaveBootCamp() {
        //GIVEN (DADO)
        List<Technology> technologies = Arrays.asList(
                new Technology(1L, "Java", "Programming Language"),
                new Technology(2L, "Python", "Programming Language"),
                new Technology(3L, "Django", "Programming Language")
        );
        List<Capacity> capacity = new ArrayList<>();

        Capacity capacity1 = new Capacity(1L, "Java", "Programming Language", technologies);
        capacity.add(capacity1);
        Capacity capacity2 = new Capacity(2L, "Python", "Programming Language", technologies);
        capacity.add(capacity2);
        Bootcamp bootCamp = new Bootcamp(1L, "Java", "Programming Language", capacity);

        //WHEN (CUANDO)
        bootCampServicePort.saveBootCamp(bootCamp);

        //THEN (ENTONCES)
        verify(bootCampPersistencePort, times(1)).saveBootCamp(bootCamp);


    }
}