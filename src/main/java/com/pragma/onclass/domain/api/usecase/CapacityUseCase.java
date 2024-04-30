package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.ICapacityServicePort;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ICapacityPersistencePort;
import com.pragma.onclass.utils.exceptions.DuplicateTechnologyException;

import java.util.ArrayList;
import java.util.List;

public class CapacityUseCase implements ICapacityServicePort {
    private ICapacityPersistencePort capacityPersistencePort;
    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort){
        this.capacityPersistencePort = capacityPersistencePort;
    }
    @Override
    public void saveCapacity(Capacity capacity){
        List<Long> technologies = new ArrayList<>();
        for (Technology technology : capacity.getTechnologies()) {
            if (capacity.getTechnologies().stream().filter(t -> t.getId().equals(technology.getId())).count() > 1) {
                throw new DuplicateTechnologyException();
            }else {
                technologies.add(technology.getId());
            }
        }

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
