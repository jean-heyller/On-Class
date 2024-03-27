package com.pragma.OnClass.adapters.driving.http.controller;

import com.pragma.OnClass.adapters.driving.http.dto.request.AddBootCampRequest;
import com.pragma.OnClass.adapters.driving.http.mapper.IBootCampRequestMapper;
import com.pragma.OnClass.domain.api.IBootCampServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class BootCampRestControllerAdapterTest {
    @Mock
    private IBootCampServicePort bootCampServicePort;
    @Mock
    private IBootCampRequestMapper bootCampRequestMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testAddBootCamp() {
        // Arrange
        BootCampRestControllerAdapter bootCampRestControllerAdapter = new BootCampRestControllerAdapter(bootCampServicePort, bootCampRequestMapper);
        List<Long> capacities = List.of(1L, 2L, 3L);
        AddBootCampRequest addBootCampRequest = new AddBootCampRequest("Java", "Programming Language", capacities);
        when(bootCampRequestMapper.addRequestToBootCamp(addBootCampRequest)).thenReturn(any());


        ResponseEntity<Void> result = bootCampRestControllerAdapter.addBootCamp(addBootCampRequest);

        // Assert
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), result);
        verify(bootCampServicePort, times(1)).saveBootCamp(any());

    }
}