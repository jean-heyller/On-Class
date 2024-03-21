package com.pragma.OnClass.domain.api.usecase;

import com.pragma.OnClass.domain.api.ICapacityServicePort;
import com.pragma.OnClass.domain.model.Capacity;
import com.pragma.OnClass.domain.spi.ICapacityPersistencePort;

public class CapacityUseCase implements ICapacityServicePort {
    private ICapacityPersistencePort capacityPersistencePort;
    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort){
        this.capacityPersistencePort = capacityPersistencePort;
    }

    public void saveCapacity(Capacity capacity){
        capacityPersistencePort.saveCapacity(capacity);
    }

    public Capacity getCapacity(String name) {
        return capacityPersistencePort.getCapacity(name);
    }

}
