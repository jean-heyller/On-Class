package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.IVersionServicePort;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.domain.spi.IVersionPersistencePort;
import com.pragma.onclass.utils.exceptions.StartDateAfterEndDateException;

public class VersionUseCase implements IVersionServicePort {

    private final IVersionPersistencePort versionPersistencePort;

    public VersionUseCase(IVersionPersistencePort versionPersistencePort) {
        this.versionPersistencePort = versionPersistencePort;
    }

    @Override
    public void saveVersion(Version version) {
        if (version.getStartDate().compareTo(version.getEndDate()) >= 0) {
            throw new StartDateAfterEndDateException();
        }
        versionPersistencePort.saveVersion(version);
    }


}
