package com.pragma.OnClass.domain.api.usecase;

import com.pragma.OnClass.domain.model.BootCamp;
import com.pragma.OnClass.domain.model.Capacity;
import com.pragma.OnClass.domain.model.Technology;
import com.pragma.OnClass.domain.spi.IBootCampPersistencePort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
class BootCampUseCaseTest {

    @Mock
    private IBootCampPersistencePort bootCampPersistencePort;

    @InjectMocks
    private BootCampUseCase bootCampServicePort;

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
        BootCamp bootCamp = new BootCamp(1L, "Java", "Programming Language", capacity);

        //WHEN (CUANDO)
        bootCampServicePort.saveBootCamp(bootCamp);

        //THEN (ENTONCES)
        verify(bootCampPersistencePort, times(1)).saveBootCamp(bootCamp);


    }
}