package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.IVersionServicePort;
import com.pragma.onclass.domain.model.Version;
import com.pragma.onclass.domain.spi.IVersionPersistencePort;

public class VersionUseCase implements IVersionServicePort {

    private final IVersionPersistencePort versionPersistencePort;

    public VersionUseCase(IVersionPersistencePort versionPersistencePort) {
        this.versionPersistencePort = versionPersistencePort;
    }

    @Override
    public void saveVersion(Version version) {
        versionPersistencePort.saveVersion(version);
    }

}
