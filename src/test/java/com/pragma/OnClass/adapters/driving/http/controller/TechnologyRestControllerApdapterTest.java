package com.pragma.OnClass.adapters.driving.http.controller;

import com.pragma.OnClass.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.pragma.OnClass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.pragma.OnClass.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.pragma.OnClass.domain.api.ITechnologyServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class TechnologyRestControllerApdapterTest {
    @Mock
    private ITechnologyServicePort technologyServicePort;
    @Mock
    private ITechnologyRequestMapper technologyRequestMapper;
    @Mock
    private ITechnologyResponseMapper technologyResponseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddTechnology() {
        // Arrange
        TechnologyRestControllerApdapter technologyRestControllerApdapter = new TechnologyRestControllerApdapter(technologyServicePort, technologyRequestMapper, technologyResponseMapper);
        // Act
        AddTechnologyRequest addTechnologyRequest = new AddTechnologyRequest("Django", "Programing Language");
        when(technologyRequestMapper.addRequestToTechnology(addTechnologyRequest)).thenReturn(any());

        ResponseEntity<Void> result = technologyRestControllerApdapter.addTechnology(addTechnologyRequest);

        // Assert
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), result);
        verify(technologyServicePort, times(1)).saveTechnology(any());
    }
}