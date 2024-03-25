package com.pragma.OnClass.adapters.driven.jpa.mysql.adapter;

import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.OnClass.adapters.driven.jpa.mysql.exception.DuplicateTechnologyException;
import com.pragma.OnClass.adapters.driven.jpa.mysql.exception.TechnologyNotFoundException;
import com.pragma.OnClass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.pragma.OnClass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.OnClass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.OnClass.domain.model.Capacity;
import com.pragma.OnClass.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CapacityAdapterTest {
    @Mock
    private ICapacityRepository capacityRepository;
    @Mock
    private ICapacityEntityMapper capacityEntityMapper;

    @Mock
    private ITechnologyRepository technologyRepository;

    @Mock
    private CapacityAdapter capacityAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        capacityAdapter = new CapacityAdapter(capacityRepository, capacityEntityMapper, technologyRepository);
    }
    @Test
    void testSaveCapacity() {
        List<Technology> technologies = List.of(new Technology(1L, "angular", "Programing Language"));
        Capacity capacity = new Capacity(1L, "Frontend","Programing Web", technologies);
        CapacityEntity capacityEntity = new CapacityEntity();
        when(capacityRepository.findByName(capacity.getName())).thenReturn(Optional.empty());
        when(capacityEntityMapper.toEntity(capacity)).thenReturn(capacityEntity);
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(new TechnologyEntity()));

        assertDoesNotThrow(() -> capacityAdapter.saveCapacity(capacity));
        verify(capacityRepository, times(1)).save(capacityEntity);


    }





    @Test
    void testDuplicatedTechnology() {

        TechnologyEntity technologyEntity = new TechnologyEntity();
        technologyEntity.setId(1L);
        technologyEntity.setName("angular");
        technologyEntity.setDescription("Programming Language");


        when(technologyRepository.findById(1L)).thenReturn(Optional.of(technologyEntity));


        List<Technology> technologies = List.of(
                new Technology(1L, "angular", "Programming Language"),
                new Technology(1L, "angular", "Programming Language")
        );
        Capacity capacity = new Capacity(1L, "Frontend", "Programing Web", technologies);


        assertThrows(DuplicateTechnologyException.class, () -> capacityAdapter.saveCapacity(capacity));
    }

    @Test
    void testTechnologyNotFound() {
        when(technologyRepository.findById(1L)).thenReturn(Optional.empty());
        List<Technology> technologies = List.of(new Technology(1L, "angular", "Programming Language"));
        Capacity capacity = new Capacity(1L, "Frontend", "Programing Web", technologies);
        assertThrows(TechnologyNotFoundException.class, () -> capacityAdapter.saveCapacity(capacity));
    }



}