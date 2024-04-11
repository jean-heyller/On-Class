package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootCampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.onclass.domain.model.Bootcamp;
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

class BootcampAdapterTest {
    @Mock
    private BootcampAdapter bootCampAdapter;

    @Mock
    ICapacityRepository capacityRepository;

    @Mock
    private IBootcampRepository bootCampRepository;

    @Mock
    private IBootCampEntityMapper bootCampEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bootCampAdapter = new BootcampAdapter(bootCampRepository, bootCampEntityMapper,capacityRepository);
    }


    @Test
    void testSaveBootCamp() {
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language"));
        technologies.add(new Technology(2L, "Python", "Programming Language"));
        technologies.add(new Technology(3L, "C++", "Programming Language"));
        List<Capacity> capacities = List.of(new Capacity(1L, "Frontend", "Web Development", technologies));
        Bootcamp bootCamp = new Bootcamp(1L, "BootCamp 1", "Web Development", capacities);

        BootcampEntity bootCampEntity = new BootcampEntity();
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

        List<BootcampEntity> entities = List.of(new BootcampEntity(1L, "BootCamp 1", "Web Development", List.of(new CapacityEntity())));

        Page<BootcampEntity> bootCampEntities = new PageImpl<>(entities);

        when(bootCampRepository.findAll(PageRequest.of(page, size))).thenReturn(bootCampEntities);
        List<Bootcamp> bootcamps = List.of(new Bootcamp(1L, "BootCamp 1", "Web Development", capacities));
        when(bootCampEntityMapper.toModelList(entities)).thenReturn(bootcamps);

        List<Bootcamp> result = bootCampAdapter.getAllBootCamps(page, size, isAscName, isAscTechnology);

        assertEquals(bootcamps, result);

        verify(bootCampRepository, times(1)).findAll(PageRequest.of(page, size));

    }

    @Test
    void testBootCampAlreadyExists() {

        Bootcamp bootcamp = new Bootcamp(1L, "bootcamp", "Description", List.of());
        BootcampEntity bootcampEntity = new BootcampEntity(1L, "bootcamp", "Description", List.of());

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
        Bootcamp bootCamp = new Bootcamp(1L, "BootCamp 1", "Web Development", capacities);

        BootcampEntity bootCampEntity = new BootcampEntity();
        when(bootCampEntityMapper.toEntity(bootCamp)).thenReturn(bootCampEntity);
        when(capacityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> bootCampAdapter.saveBootCamp(bootCamp));

    }
}