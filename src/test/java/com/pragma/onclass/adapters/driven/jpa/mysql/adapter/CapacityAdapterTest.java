package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootCampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.onclass.utils.exceptions.DuplicateTechnologyException;
import com.pragma.onclass.utils.exceptions.TechnologyNotFoundException;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

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

        when(capacityEntityMapper.toEntity(capacity)).thenReturn(capacityEntity);
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(new TechnologyEntity()));

        assertDoesNotThrow(() -> capacityAdapter.saveCapacity(capacity));
        verify(capacityRepository, times(1)).save(capacityEntity);


    }
    @Test
    void testGetAllCapacities(){
        // Arrange
        int page = 0;
        int size = 10;
        boolean isAscName = true;
        boolean isAscTechnology = true;


        List<Technology> technologies = Arrays.asList(
                new Technology(1L, "Java", "Programming"),
                new Technology(2L, "Python", "Programming")
        );


        List<CapacityEntity> entities = Arrays.asList(
                new CapacityEntity(1L, "Capacity 1", "description 1",
                        Collections.singletonList(new TechnologyEntity()),Collections.singletonList(new BootCampEntity())),
                new CapacityEntity(2L, "Capacity 2", "description 2", Arrays.asList(new TechnologyEntity(), new TechnologyEntity()),Collections.singletonList(new BootCampEntity()))
        );


        Page<CapacityEntity> pageOfEntities = new PageImpl<>(entities);

        when(capacityRepository.findAll(PageRequest.of(page, size))).thenReturn(pageOfEntities);


        List<Capacity> expectedCapacities = Arrays.asList(
                new Capacity(1L, "Capacity 1", "description 1", technologies),
                new Capacity(2L, "Capacity 2", "description 2", technologies)
        );


        when(capacityEntityMapper.toModelist(entities)).thenReturn(expectedCapacities);

        List<Capacity> actualCapacities = capacityAdapter.getAllCapacities(page, size, isAscName, isAscTechnology);


        assertEquals(expectedCapacities, actualCapacities);
        verify(capacityRepository).findAll(PageRequest.of(page, size));verify(capacityEntityMapper).toModelist(entities);



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