package com.pragma.onclass.adapters.driving.http.controller;

import com.pragma.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.onclass.adapters.driving.http.mapper.IVersionRequestMapper;
import com.pragma.onclass.domain.api.IVersionServicePort;
import com.pragma.onclass.domain.model.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class VersionRestControllerAdapterTest {

    private IVersionServicePort versionServicePort;
    private IVersionRequestMapper versionRequestMapper;
    private VersionRestControllerAdapter versionRestControllerAdapter;

    @BeforeEach
    void setUp() {
        versionServicePort = Mockito.mock(IVersionServicePort.class);
        versionRequestMapper = Mockito.mock(IVersionRequestMapper.class);
        versionRestControllerAdapter = new VersionRestControllerAdapter(versionServicePort, versionRequestMapper);
    }

    @Test
    void addVersion_validRequest_callsServiceAndReturnsOk() {
        AddVersionRequest request = new AddVersionRequest(LocalDate.now(), LocalDate.now().plusDays(1), 10, 1L);
        Version version = new Version(1L, LocalDate.now(), LocalDate.now().plusDays(1), 10, null);
        Mockito.when(versionRequestMapper.addRequestToVersion(request)).thenReturn(version);

        ResponseEntity<Void> response = versionRestControllerAdapter.addVersion(request);

        verify(versionServicePort).saveVersion(version);
        assertEquals(200, response.getStatusCodeValue());
    }

    // Add more tests for invalid requests here
}