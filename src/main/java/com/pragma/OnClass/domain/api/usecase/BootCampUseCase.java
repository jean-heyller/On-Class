package com.pragma.OnClass.domain.api.usecase;

import com.pragma.OnClass.domain.api.IBootCampServicePort;
import com.pragma.OnClass.domain.model.BootCamp;
import com.pragma.OnClass.domain.spi.IBootCampPersistencePort;

public class BootCampUseCase implements IBootCampServicePort {
    private IBootCampPersistencePort bootCampPersistencePort;
    public BootCampUseCase(IBootCampPersistencePort bootCampPersistencePort){
        this.bootCampPersistencePort = bootCampPersistencePort;
    }
    @Override
    public void saveBootCamp(BootCamp bootCamp){
        bootCampPersistencePort.saveBootCamp(bootCamp);
    }
}
