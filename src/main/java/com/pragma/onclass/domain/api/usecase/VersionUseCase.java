package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.IVersionServicePort;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.domain.spi.IVersionPersistencePort;
import com.pragma.onclass.utils.exceptions.StartDateAfterEndDateException;
import com.pragma.onclass.utils.exceptions.StartDateBeforeCurrentDateException;

import java.time.LocalDate;


public class VersionUseCase implements IVersionServicePort {

    private final IVersionPersistencePort versionPersistencePort;

    public VersionUseCase(IVersionPersistencePort versionPersistencePort) {
        this.versionPersistencePort = versionPersistencePort;
    }


    @Override
    public void saveVersion(Version version) {
        LocalDate currentDate = LocalDate.now();
        if ( version.getStartDate().isAfter(version.getEndDate())) {
            throw new StartDateAfterEndDateException();
        }

        if (version.getStartDate().isBefore(currentDate)) {
            throw new StartDateBeforeCurrentDateException();
        }
        versionPersistencePort.saveVersion(version);
    }
}





