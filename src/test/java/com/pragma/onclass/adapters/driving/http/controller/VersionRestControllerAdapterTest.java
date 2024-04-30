package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.driving.http.dto.TypeDtoBootcamp;
import com.pragma.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.onclass.adapters.driving.http.dto.response.VersionResponse;
import com.pragma.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.pragma.onclass.adapters.driving.http.mapper.IVersionResponseMapper;
import com.pragma.onclass.domain.api.IVersionServicePort;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class VersionRestControllerAdapterTest {

    private IVersionServicePort versionServicePort;


    private IVersionRequestMapper versionRequestMapper;


    private IVersionResponseMapper versionResponseMapper;

    private VersionRestControllerAdapter versionRestControllerAdapter;

    @BeforeEach
    void setUp() {
        versionServicePort = Mockito.mock(IVersionServicePort.class);
        versionRequestMapper = Mockito.mock(IVersionRequestMapper.class);
        versionResponseMapper = Mockito.mock(IVersionResponseMapper.class);
        versionRestControllerAdapter = new VersionRestControllerAdapter(versionServicePort, versionRequestMapper,versionResponseMapper);
    }

    @Test
    void addVersion_validRequest_callsServiceAndReturnsOk() {
        AddVersionRequest request = new AddVersionRequest(LocalDate.now(), LocalDate.now().plusDays(1), 10, 1L);
        Version version = new Version(1L, LocalDate.now(), LocalDate.now().plusDays(1), 10, null);
        when(versionRequestMapper.addRequestToVersion(request)).thenReturn(version);

        ResponseEntity<Void> response = versionRestControllerAdapter.addVersion(request);

        verify(versionServicePort).saveVersion(version);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getAllVersions_validRequest_callsServiceAndReturnsOk() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        String isAscName = "";
        String isAscDate = "";
        String isAscQuota = "";
        Long bootcampId = null;

        List<Version> versions = Collections.singletonList(new Version(1L, LocalDate.now(), LocalDate.now().plusDays(1), 10, null));
        TypeDtoBootcamp typeDtoBootcamp = new TypeDtoBootcamp(1L,"Bootcamp"); // Crear un objeto TypeDtoBootcamp
        List<VersionResponse> versionResponses = Collections.singletonList(new VersionResponse(LocalDate.now(), LocalDate.now().plusDays(1), 10, typeDtoBootcamp));

        when(versionServicePort.getAllVersions(page, size, isAscName, isAscDate, isAscQuota, bootcampId)).thenReturn(versions);
        when(versionResponseMapper.toVersionResponseList(versions)).thenReturn(versionResponses);

        // Act
        ResponseEntity<List<VersionResponse>> response = versionRestControllerAdapter.getAllVersions(page, size, isAscName, isAscDate, isAscQuota, bootcampId);

        // Assert
        verify(versionServicePort).getAllVersions(page, size, isAscName, isAscDate, isAscQuota, bootcampId);
        verify(versionResponseMapper).toVersionResponseList(versions);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(versionResponses, response.getBody());
    }













    }