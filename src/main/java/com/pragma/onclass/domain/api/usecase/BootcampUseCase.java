package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.IBootcampServicePort;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.spi.IBootcampPersistencePort;
import com.pragma.onclass.utils.exceptions.DuplicateCapacityException;

import java.util.ArrayList;
import java.util.List;

public class BootcampUseCase implements IBootcampServicePort {
    private IBootcampPersistencePort bootCampPersistencePort;
    public BootcampUseCase(IBootcampPersistencePort bootCampPersistencePort){
        this.bootCampPersistencePort = bootCampPersistencePort;
    }
    @Override
    public void saveBootCamp(Bootcamp bootCamp){
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
    public List<Bootcamp> getAllBootCamps(Integer page, Integer size, boolean isAscName, boolean isAscCapacity){
        return bootCampPersistencePort.getAllBootCamps(page, size, isAscName, isAscCapacity);
    }
}
