package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootCampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.utils.exceptions.NoDataFoundException;
import com.pragma.onclass.utils.exceptions.ValueAlreadyExitsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
}