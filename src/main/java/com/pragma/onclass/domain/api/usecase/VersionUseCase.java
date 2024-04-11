package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.IVersionServicePort;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.domain.spi.IVersionPersistencePort;
import com.pragma.onclass.utils.exceptions.StartDateAfterEndDateException;

import java.util.Date;

public class VersionUseCase implements IVersionServicePort {

    private final IVersionPersistencePort versionPersistencePort;

    public VersionUseCase(IVersionPersistencePort versionPersistencePort) {
        this.versionPersistencePort = versionPersistencePort;
    }

    @Override
    public void saveVersion(Version version) {
        Date currentDate = new Date();
        if (version.getStartDate().before(currentDate) || version.getStartDate().after(version.getEndDate())) {
            throw new StartDateAfterEndDateException();
        }
        versionPersistencePort.saveVersion(version);
    }




}
