package com.pragma.OnClass.adapters.driving.http.controller;

import com.pragma.OnClass.adapters.driving.http.dto.TypeDtoTechnology;
import com.pragma.OnClass.adapters.driving.http.dto.request.AddCapacityRequest;
import com.pragma.OnClass.adapters.driving.http.dto.response.CapacityResponse;
import com.pragma.OnClass.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.pragma.OnClass.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.pragma.OnClass.domain.api.ICapacityServicePort;

import com.pragma.OnClass.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

class CapacityRestControllerAdapterTest {
    @Mock
    private ICapacityServicePort capacityServicePort;

    @Mock
    private ICapacityRequestMapper capacityRequestMapper;

    @Mock
    private ICapacityResponseMapper capacityResponseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void testAddCapacity() {
        // Arrange
        CapacityRestControllerAdapter capacityRestControllerAdapter = new CapacityRestControllerAdapter(capacityServicePort, capacityRequestMapper,capacityResponseMapper);
        List<Long> technologies = Arrays.asList(1L, 2L, 3L);
        AddCapacityRequest addCapacityRequest = new AddCapacityRequest("Java", "Programming Language", technologies);
        when(capacityRequestMapper.addRequestToCapacity(addCapacityRequest)).thenReturn(any());

        ResponseEntity<Void> result = capacityRestControllerAdapter.addCapacity(addCapacityRequest);

        // Assert
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), result);
        verify(capacityServicePort, times(1)).saveCapacity(any());

    }

    @Test
    void testGetAllCapacities() {
        Integer size = 1;
        Integer page = 10;
        boolean isAscName = true;
        boolean isAscTechnology = true;
        // Arrange
        CapacityRestControllerAdapter capacityRestControllerAdapter = new CapacityRestControllerAdapter(capacityServicePort, capacityRequestMapper,capacityResponseMapper);

        List<TypeDtoTechnology> technologies = Arrays.asList(
                new TypeDtoTechnology(1L, "Java"),
                new TypeDtoTechnology(2L, "Python"),
                new TypeDtoTechnology(3L, "Django")
        );
        List<CapacityResponse> responseList = Arrays.asList(new CapacityResponse(1L, "Java", "Programming Language",technologies));
        when(capacityServicePort.getAllCapacities(size,page,isAscName,isAscTechnology)).thenReturn(Collections.emptyList());

        when(capacityResponseMapper.toCapacityResponseList(Collections.emptyList())).thenReturn(responseList);

        ResponseEntity<List<CapacityResponse>> result = capacityRestControllerAdapter.getAllCapacities(1, 1, true, true);


        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseList, result.getBody());
        verify(capacityServicePort, times(1)).getAllCapacities(1, 1, true, true);
        verify(capacityResponseMapper, times(1)).toCapacityResponseList(any());
    }
}