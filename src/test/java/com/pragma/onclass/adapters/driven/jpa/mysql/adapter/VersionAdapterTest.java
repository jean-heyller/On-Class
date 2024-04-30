package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootCampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.utils.exceptions.IncompatibleValueException;
import com.pragma.onclass.utils.exceptions.NoDataFoundException;
import com.pragma.onclass.utils.exceptions.ValueAlreadyExitsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VersionAdapterTest {

    private IVersionRepository versionRepository;
    private IVersionEntityMapper versionEntityMapper;
    private IBootCampEntityMapper bootCampEntityMapper;
    private IBootcampRepository bootCampRepository;
    private VersionAdapter versionAdapter;

    @BeforeEach
    void setUp() {
        versionRepository = Mockito.mock(IVersionRepository.class);
        versionEntityMapper = Mockito.mock(IVersionEntityMapper.class);
        bootCampEntityMapper = Mockito.mock(IBootCampEntityMapper.class);
        bootCampRepository = Mockito.mock(IBootcampRepository.class);
        versionAdapter = new VersionAdapter(versionRepository, versionEntityMapper, bootCampEntityMapper, bootCampRepository);
    }

    @Test
    void saveVersion_validVersion_savesVersion() {
        Version version = new Version(1L, LocalDate.now(), LocalDate.now().plusDays(1), 10, new Bootcamp(1L, "Bootcamp1", "Description", null));
        BootcampEntity bootcampEntity = new BootcampEntity(1L, "Bootcamp1", "Description", null);
        VersionEntity versionEntity = new VersionEntity(1L, LocalDate.now(), LocalDate.now().plusDays(1), 10, bootcampEntity);

        when(bootCampRepository.findById(1L)).thenReturn(Optional.of(bootcampEntity));
        when(versionRepository.findByStartDateAndEndDateAndMaximumQuotaAndBootcamp(LocalDate.now(), LocalDate.now().plusDays(1), 10, bootcampEntity)).thenReturn(null);
        when(versionEntityMapper.toEntity(version)).thenReturn(versionEntity);

        versionAdapter.saveVersion(version);

        verify(versionRepository).save(versionEntity);
    }

    @Test
    void saveVersion_existingVersion_throwsException() {
        Version version = new Version(1L, LocalDate.now(), LocalDate.now().plusDays(1), 10, new Bootcamp(1L, "Bootcamp1", "Description", null));
        BootcampEntity bootcampEntity = new BootcampEntity(1L, "Bootcamp1", "Description", null);
        VersionEntity versionEntity = new VersionEntity(1L, LocalDate.now(), LocalDate.now().plusDays(1), 10, bootcampEntity);

        when(bootCampRepository.findById(1L)).thenReturn(Optional.of(bootcampEntity));
        when(versionRepository.findByStartDateAndEndDateAndMaximumQuotaAndBootcamp(LocalDate.now(), LocalDate.now().plusDays(1), 10, bootcampEntity)).thenReturn(versionEntity);

        assertThrows(ValueAlreadyExitsException.class, () -> versionAdapter.saveVersion(version));
    }

    @Test
    void saveVersion_nonExistingBootcamp_throwsException() {
        Version version = new Version(1L, LocalDate.now(), LocalDate.now().plusDays(1), 10, new Bootcamp(1L, "Bootcamp1", "Description", null));

        when(bootCampRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> versionAdapter.saveVersion(version));
    }

    @Test
    void getAllVersions_returnsVersions() {
        // Preparar
        BootcampEntity bootcampEntity = new BootcampEntity(1L, "Bootcamp1", "Description", null);
        VersionEntity versionEntity = new VersionEntity(1L, LocalDate.now(), LocalDate.now().plusDays(1), 10, bootcampEntity);
        Page<VersionEntity> versionEntities = new PageImpl<>(Collections.singletonList(versionEntity));
        Version version = new Version(1L, LocalDate.now(), LocalDate.now().plusDays(1), 10, null);
        List<Version> expectedVersions = Collections.singletonList(version);

        when(versionRepository.findAll(any(Pageable.class))).thenReturn(versionEntities);
        when(versionEntityMapper.toModelList(versionEntities.getContent())).thenReturn(expectedVersions);

        // Ejecutar
        List<Version> actualVersions = versionAdapter.getAllVersions(0, 10, "asc", null, null, null);

        // Verificar
        assertEquals(expectedVersions, actualVersions);
        verify(versionRepository).findAll(any(Pageable.class));
        verify(versionEntityMapper).toModelList(versionEntities.getContent());
    }






    @Test
    void getAllVersions_throwsIncompatibleValueException() {
        // Arrange
        int invalidPage = -1;
        int invalidSize = -1;

        // Act & Assert
        assertThrows(IncompatibleValueException.class, () -> versionAdapter.getAllVersions(invalidPage, invalidSize, null, null, null, null));
    }
}