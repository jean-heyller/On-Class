package com.pragma.OnClass.adapters.driven.jpa.mysql.adapter;

import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.OnClass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.OnClass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.OnClass.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
class TechnologyAdapterTest {
    @Mock
    private ITechnologyRepository technologyRepository;
    @Mock
    private ITechnologyEntityMapper technologyEntityMapper;

    @Mock
    private TechnologyAdapter technologyAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        technologyAdapter = new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }

    @Test
    void testSaveTechnology() {
        Technology technology = new Technology(6L, "Django", "Programing Language");
        TechnologyEntity technologyEntity = new TechnologyEntity();
        when(technologyRepository.findByName(technology.getName())).thenReturn(Optional.empty());
        when(technologyEntityMapper.toEntity(technology)).thenReturn(technologyEntity);

        assertDoesNotThrow(() -> technologyAdapter.saveTechnology(technology));
        verify(technologyRepository, times(1)).save(technologyEntity);
    }
    @Test
    void testSaveTechnologyWhenTechnologyAlreadyExists() {
        Technology technology = new Technology(6L, "Django", "Programing Language");
        when(technologyRepository.findByName(technology.getName())).thenReturn(Optional.of(new TechnologyEntity()));

        assertDoesNotThrow(() -> technologyAdapter.saveTechnology(technology));

    }
}