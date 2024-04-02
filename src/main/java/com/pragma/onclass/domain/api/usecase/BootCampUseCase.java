package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.IBootCampServicePort;
import com.pragma.onclass.domain.model.BootCamp;
import com.pragma.onclass.domain.spi.IBootCampPersistencePort;

import java.util.List;

public class BootCampUseCase implements IBootCampServicePort {
    private IBootCampPersistencePort bootCampPersistencePort;
    public BootCampUseCase(IBootCampPersistencePort bootCampPersistencePort){
        this.bootCampPersistencePort = bootCampPersistencePort;
    }
    @Override
    public void saveBootCamp(BootCamp bootCamp){
        bootCampPersistencePort.saveBootCamp(bootCamp);
    }

    @Override
    public List<BootCamp> getAllBootCamps(Integer page, Integer size, boolean isAscName, boolean isAscCapacity){
        return bootCampPersistencePort.getAllBootCamps(page, size, isAscName, isAscCapacity);
    }
}
