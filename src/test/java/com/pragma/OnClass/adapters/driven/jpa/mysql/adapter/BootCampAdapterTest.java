package com.pragma.OnClass.adapters.driven.jpa.mysql.adapter;

import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.BootCampEntity;
import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.OnClass.adapters.driven.jpa.mysql.mapper.IBootCampEntityMapper;
import com.pragma.OnClass.adapters.driven.jpa.mysql.repository.IBootCampRepository;
import com.pragma.OnClass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.OnClass.domain.model.BootCamp;
import com.pragma.OnClass.domain.model.Capacity;
import com.pragma.OnClass.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class BootCampAdapterTest {
    @Mock
    private BootCampAdapter bootCampAdapter;

    @Mock
    ICapacityRepository capacityRepository;

    @Mock
    private IBootCampRepository bootCampRepository;

    @Mock
    private IBootCampEntityMapper bootCampEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bootCampAdapter = new BootCampAdapter(bootCampRepository, bootCampEntityMapper,capacityRepository);
    }


    @Test
    void testSaveBootCamp() {
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language"));
        technologies.add(new Technology(2L, "Python", "Programming Language"));
        technologies.add(new Technology(3L, "C++", "Programming Language"));
        List<Capacity> capacities = List.of(new Capacity(1L, "Frontend", "Web Development", technologies));
        BootCamp bootCamp = new BootCamp(1L, "BootCamp 1", "Web Development", capacities);

        BootCampEntity bootCampEntity = new BootCampEntity();
        when(bootCampEntityMapper.toEntity(bootCamp)).thenReturn(bootCampEntity);
        when(capacityRepository.findById(1L)).thenReturn(java.util.Optional.of(new CapacityEntity()));

        assertDoesNotThrow(() -> bootCampAdapter.saveBootCamp(bootCamp));
        verify(bootCampRepository, times(1)).save(bootCampEntity);


    }
}