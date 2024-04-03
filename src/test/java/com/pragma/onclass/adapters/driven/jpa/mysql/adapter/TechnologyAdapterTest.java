package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.onclass.utils.exceptions.IncompatibleValueException;
import com.pragma.onclass.utils.exceptions.NoDataFoundException;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.onclass.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    @Test
    void testGetTechnologyCorrect() {
        int page = 0;
        int size = 10;
        boolean isAsc = true;

        PageRequest pageRequest = PageRequest.of(page, size, isAsc ? org.springframework.data.domain.Sort.by("name").ascending() : org.springframework.data.domain.Sort.by("name").descending());
        TechnologyEntity technologyEntity = new TechnologyEntity();
        technologyEntity.setName("Django");
        Page<TechnologyEntity> technologyEntityPage = new PageImpl<>(Collections.singletonList(technologyEntity),pageRequest,1);
        when(technologyRepository.findAll(pageRequest)).thenReturn(technologyEntityPage);
        when(technologyEntityMapper.toModelist(Collections.singletonList(technologyEntity))).thenReturn(Collections.singletonList(new Technology(1L, "Django", "Programing Language")));

        List<Technology>  resultList = Collections.singletonList(new Technology(1L, "Django", "Programing Language"));
        List<Technology> nowList = technologyAdapter.getAllTechnologies(page, size, isAsc);

        assertEquals(resultList.size(), nowList.size());
        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(resultList.get(i).getName(), nowList.get(i).getName());
        }

        verify(technologyRepository, times(1)).findAll(pageRequest);
        verify(technologyEntityMapper, times(1)).toModelist(Collections.singletonList(technologyEntity));


    }
    @Test
    void testGetTechnologyWhenPageIsNegative() {
        int page = -1;
        int size = 10;
        boolean isAsc = true;

        assertThrows(IncompatibleValueException.class, () -> technologyAdapter.getAllTechnologies(page, size, isAsc));
    }

    @Test
    void testGetTechnologyWhenNoDataFound() {
        int page = 0;
        int size = 10;
        boolean isAsc = true;

        PageRequest pageRequest = PageRequest.of(page, size, isAsc ? org.springframework.data.domain.Sort.by("name").ascending() : org.springframework.data.domain.Sort.by("name").descending());
        Page<TechnologyEntity> technologyEntityPage = new PageImpl<>(Collections.emptyList(),pageRequest,0);
        when(technologyRepository.findAll(pageRequest)).thenReturn(technologyEntityPage);

        assertThrows(NoDataFoundException.class, () -> technologyAdapter.getAllTechnologies(page, size, isAsc));
        verify(technologyRepository, times(1)).findAll(pageRequest);
        verifyNoInteractions(technologyEntityMapper);
    }
}