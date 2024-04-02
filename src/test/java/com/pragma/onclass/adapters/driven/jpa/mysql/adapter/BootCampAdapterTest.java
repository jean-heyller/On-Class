package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootCampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootCampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootCampRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.onclass.domain.model.BootCamp;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.utils.exceptions.BootCampAlreadyExitsException;
import com.pragma.onclass.utils.exceptions.NoDataFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    void testGetAllBootCamps() {
        Integer page = 0;
        Integer size = 10;
        boolean isAscName = true;
        boolean isAscTechnology = true;

        List<Capacity> capacities = List.of(new Capacity(1L, "Frontend", "Web Development", List.of(new Technology(1L, "Java", "Programming Language"))));

        List<BootCampEntity> entities = List.of(new BootCampEntity(1L, "BootCamp 1", "Web Development", List.of(new CapacityEntity())));

        Page<BootCampEntity> bootCampEntities = new PageImpl<>(entities);

        when(bootCampRepository.findAll(PageRequest.of(page, size))).thenReturn(bootCampEntities);
        List<BootCamp> bootCamps = List.of(new BootCamp(1L, "BootCamp 1", "Web Development", capacities));
        when(bootCampEntityMapper.toModelList(entities)).thenReturn(bootCamps);

        List<BootCamp> result = bootCampAdapter.getAllBootCamps(page, size, isAscName, isAscTechnology);

        assertEquals(bootCamps, result);

        verify(bootCampRepository, times(1)).findAll(PageRequest.of(page, size));

    }

    @Test
    void testBootCampAlreadyExists() {

        BootCamp bootcamp = new BootCamp(1L, "bootcamp", "Description", List.of());
        BootCampEntity bootcampEntity = new BootCampEntity(1L, "bootcamp", "Description", List.of());

        when(bootCampRepository.findByName(bootcamp.getName())).thenReturn(Optional.of(bootcampEntity));


        assertThrows(BootCampAlreadyExitsException.class, () -> bootCampAdapter.saveBootCamp(bootcamp));


        verify(bootCampRepository).findByName(bootcamp.getName());

    }

    @Test
    void testCapacityNotFound() {
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language"));
        technologies.add(new Technology(2L, "Python", "Programming Language"));
        technologies.add(new Technology(3L, "C++", "Programming Language"));
        List<Capacity> capacities = List.of(new Capacity(1L, "Frontend", "Web Development", technologies));
        BootCamp bootCamp = new BootCamp(1L, "BootCamp 1", "Web Development", capacities);

        BootCampEntity bootCampEntity = new BootCampEntity();
        when(bootCampEntityMapper.toEntity(bootCamp)).thenReturn(bootCampEntity);
        when(capacityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> bootCampAdapter.saveBootCamp(bootCamp));

    }
}