package com.pragma.OnClass.domain.api.usecase;

import com.pragma.OnClass.domain.api.ITechnologyServicePort;
import com.pragma.OnClass.domain.model.Technology;
import com.pragma.OnClass.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TechnologyUseCaseTest {
    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;
    private ITechnologyServicePort technologyServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        technologyServicePort = new TechnologyUseCase(technologyPersistencePort);
    }
    @Test
    void testSaveTechnology() {
        // Arrange
        Technology technology = new Technology(6L, "Django", "Programing Language");
        // Act
        technologyServicePort.saveTechnology(technology);
        // Assert
        verify(technologyPersistencePort, times(1)).saveTechnology(technology);
    }



    @Test
    void testGetAllTechnologies() {
        List<Technology>  technologies =  new ArrayList<>();
        technologies.add(new Technology(6L, "Django", "Programing Language"));
        technologies.add(new Technology(7L, "Java", "Programing Language"));
        technologies.add(new Technology(8L, "Python", "Programing Language"));

        when(technologyPersistencePort.getAllTechnologies(1, 10, true)).thenReturn(technologies);
        List<Technology> result = technologyServicePort.getAllTechnologies(1, 10, true);

        assertEquals(technologies.size(), result.size());
        for (int i = 0; i < technologies.size(); i++) {
            assertEquals(technologies.get(i).getName(), result.get(i).getName());
        }
    }
}