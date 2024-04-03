package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.IBootCampServicePort;
import com.pragma.onclass.domain.model.BootCamp;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.spi.IBootCampPersistencePort;
import com.pragma.onclass.utils.exceptions.DuplicateCapacityException;

import java.util.ArrayList;
import java.util.List;

public class BootCampUseCase implements IBootCampServicePort {
    private IBootCampPersistencePort bootCampPersistencePort;
    public BootCampUseCase(IBootCampPersistencePort bootCampPersistencePort){
        this.bootCampPersistencePort = bootCampPersistencePort;
    }
    @Override
    public void saveBootCamp(BootCamp bootCamp){
        List<Long> capacities = new ArrayList<>();

        for (Capacity capacity : bootCamp.getCapacities()) {
            if (bootCamp.getCapacities().stream().filter(c -> c.getId().equals(capacity.getId())).count() > 1) {
                throw new DuplicateCapacityException();
            }else {
                capacities.add(capacity.getId());
            }
        }

        bootCampPersistencePort.saveBootCamp(bootCamp);
    }

    @Override
    public List<BootCamp> getAllBootCamps(Integer page, Integer size, boolean isAscName, boolean isAscCapacity){
        return bootCampPersistencePort.getAllBootCamps(page, size, isAscName, isAscCapacity);
    }
}
