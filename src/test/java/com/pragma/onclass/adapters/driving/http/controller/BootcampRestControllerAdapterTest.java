package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.onclass.adapters.driving.http.dto.TypeDtoCapacity;
import com.pragma.onclass.adapters.driving.http.dto.TypeDtoTechnology;
import com.pragma.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.BootCampResponse;
import com.pragma.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.pragma.onclass.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.pragma.onclass.domain.api.IBootcampServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class BootcampRestControllerAdapterTest {
    @Mock
    private IBootcampServicePort bootCampServicePort;
    @Mock
    private IBootcampRequestMapper bootCampRequestMapper;

    @Mock
    private IBootcampResponseMapper bootCampResponseMapper;

    @Mock
    private ICapacityRepository capacityRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testAddBootCamp() {
        // Arrange
        BootcampRestControllerAdapter bootCampRestControllerAdapter = new BootcampRestControllerAdapter(bootCampServicePort, bootCampRequestMapper,bootCampResponseMapper);
        List<Long> capacities = List.of(1L, 2L, 3L);
        AddBootcampRequest addBootCampRequest = new AddBootcampRequest("Java", "Programming Language", capacities);
        when(bootCampRequestMapper.addRequestToBootCamp(addBootCampRequest)).thenReturn(any());


        ResponseEntity<Void> result = bootCampRestControllerAdapter.addBootCamp(addBootCampRequest);

        // Assert
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), result);
        verify(bootCampServicePort, times(1)).saveBootCamp(any());

    }
    @Test
    void testGetAllBootCamps() {
        Integer size = 1;
        Integer page = 0;
        boolean isAscName = true;
        boolean isAscTechnology = true;

        List<TypeDtoTechnology> typeDtoTechnologies = List.of(new TypeDtoTechnology(1L, "Java"));
        BootcampRestControllerAdapter bootCampRestControllerAdapter = new BootcampRestControllerAdapter(bootCampServicePort, bootCampRequestMapper,bootCampResponseMapper);

        List<TypeDtoCapacity> typeDtoCapacities = List.of(new TypeDtoCapacity(1L, "Java",typeDtoTechnologies));
        List<BootCampResponse> bootCampResponses = List.of(new BootCampResponse(1L, "Java", "Programming Language", typeDtoCapacities));
        when(bootCampServicePort.getAllBootCamps(size,page,isAscName,isAscTechnology)).thenReturn(Collections.emptyList());

        when(bootCampResponseMapper.toBootCampResponseList(Collections.emptyList())).thenReturn(bootCampResponses);

        ResponseEntity<List<BootCampResponse>> result = bootCampRestControllerAdapter.getAllBootCamps(size, page, isAscName, isAscTechnology);

        assertEquals(ResponseEntity.ok(bootCampResponses), result);
        assertEquals(bootCampResponses, result.getBody());
        verify(bootCampServicePort, times(1)).getAllBootCamps(size,page,isAscName,isAscTechnology);
        verify(bootCampResponseMapper, times(1)).toBootCampResponseList(Collections.emptyList());





    }
}