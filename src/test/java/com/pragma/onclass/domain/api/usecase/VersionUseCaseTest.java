package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.IVersionServicePort;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.domain.spi.IVersionPersistencePort;
import com.pragma.onclass.utils.exceptions.StartDateAfterEndDateException;
import com.pragma.onclass.utils.exceptions.StartDateBeforeCurrentDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VersionUseCaseTest {

    private IVersionPersistencePort versionPersistencePort;
    private IVersionServicePort versionServicePort;

    @BeforeEach
    void setUp() {
        versionPersistencePort = Mockito.mock(IVersionPersistencePort.class);
        versionServicePort = new VersionUseCase(versionPersistencePort);
    }

    @Test
    void saveVersion_startDateAfterEndDate_throwsException() {
        List<Technology> technologies = Collections.singletonList(new Technology(1L, "Tech1", "Description"));
        Capacity capacity = new Capacity(1L, "Capacity1", "Description", technologies);
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp1", "Description", Collections.singletonList(capacity));
        Version version = new Version(1L, LocalDate.now().plusDays(1), LocalDate.now(), 10, bootcamp);

        assertThrows(StartDateAfterEndDateException.class, () -> versionServicePort.saveVersion(version));
    }

    @Test
    void saveVersion_startDateBeforeCurrentDate_throwsException() {
        List<Technology> technologies = Collections.singletonList(new Technology(1L, "Tech1", "Description"));
        Capacity capacity = new Capacity(1L, "Capacity1", "Description", technologies);
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp1", "Description", Collections.singletonList(capacity));
        Version version = new Version(1L, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1), 10, bootcamp);

        assertThrows(StartDateBeforeCurrentDateException.class, () -> versionServicePort.saveVersion(version));
    }

    @Test
    void saveVersion_endDateBeforeStartDate_throwsException() {
        List<Technology> technologies = Collections.singletonList(new Technology(1L, "Tech1", "Description"));
        Capacity capacity = new Capacity(1L, "Capacity1", "Description", technologies);
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp1", "Description", Collections.singletonList(capacity));
        Version version = new Version(1L, LocalDate.now(), LocalDate.now().minusDays(1), 10, bootcamp);

        assertThrows(StartDateAfterEndDateException.class, () -> versionServicePort.saveVersion(version));
    }

    @Test
    void saveVersion_validDates_savesVersion() {
        List<Technology> technologies = Collections.singletonList(new Technology(1L, "Tech1",   "Description"));
        Capacity capacity = new Capacity(1L, "Capacity1", "Description", technologies);
        Bootcamp bootcamp = new Bootcamp(1L, "Bootcamp1", "Description", Collections.singletonList(capacity));
        Version version = new Version(1L, LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), 10, bootcamp);

        versionServicePort.saveVersion(version);

        verify(versionPersistencePort).saveVersion(version);
    }

    @Test
    void getAllVersions_returnsVersions() {

        List<Version> expectedVersions = Collections.singletonList(
                new Version(1L, LocalDate.now(), LocalDate.now().plusDays(1), 10, null)
        );
        when(versionPersistencePort.getAllVersions(0, 10, "asc", "asc", "asc", 1L)).thenReturn(expectedVersions);


        List<Version> actualVersions = versionServicePort.getAllVersions(0, 10, "asc", "asc", "asc", 1L);


        assertEquals(expectedVersions, actualVersions);
        verify(versionPersistencePort).getAllVersions(0, 10, "asc", "asc", "asc", 1L);
    }
}