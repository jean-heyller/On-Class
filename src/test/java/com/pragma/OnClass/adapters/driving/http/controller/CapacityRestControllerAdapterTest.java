package com.pragma.OnClass.adapters.driving.http.controller;

import com.pragma.OnClass.adapters.driving.http.dto.request.AddCapacityRequest;
import com.pragma.OnClass.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.pragma.OnClass.domain.api.ICapacityServicePort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

class CapacityRestControllerAdapterTest {
    @Mock
    private ICapacityServicePort capacityServicePort;

    @Mock
    private ICapacityRequestMapper capacityRequestMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void testAddCapacity() {
        // Arrange
        CapacityRestControllerAdapter capacityRestControllerAdapter = new CapacityRestControllerAdapter(capacityServicePort, capacityRequestMapper);
        List<Long> technologies = Arrays.asList(1L, 2L, 3L);
        AddCapacityRequest addCapacityRequest = new AddCapacityRequest("Java", "Programming Language", technologies);
        when(capacityRequestMapper.addRequestToCapacity(addCapacityRequest)).thenReturn(any());

        ResponseEntity<Void> result = capacityRestControllerAdapter.addCapacity(addCapacityRequest);

        // Assert
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), result);
        verify(capacityServicePort, times(1)).saveCapacity(any());

    }
}