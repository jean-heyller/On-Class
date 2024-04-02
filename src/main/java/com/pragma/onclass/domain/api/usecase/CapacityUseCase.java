package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.ICapacityServicePort;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.spi.ICapacityPersistencePort;

import java.util.List;

public class CapacityUseCase implements ICapacityServicePort {
    private ICapacityPersistencePort capacityPersistencePort;
    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort){
        this.capacityPersistencePort = capacityPersistencePort;
    }
    @Override
    public void saveCapacity(Capacity capacity){
        capacityPersistencePort.saveCapacity(capacity);
    }
    @Override
    public Capacity getCapacity(String name) {
        return capacityPersistencePort.getCapacity(name);
    }
    @Override
    public List<Capacity> getAllCapacities(Integer page, Integer size, boolean isAscName, boolean isAscTechnology) {
        return capacityPersistencePort.getAllCapacities(page,size,isAscName,isAscTechnology);
    }

}
