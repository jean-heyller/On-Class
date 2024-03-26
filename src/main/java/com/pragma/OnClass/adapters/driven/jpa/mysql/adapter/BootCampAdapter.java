package com.pragma.OnClass.adapters.driven.jpa.mysql.adapter;

import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.BootCampEntity;
import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.OnClass.adapters.driven.jpa.mysql.mapper.IBootCampEntityMapper;
import com.pragma.OnClass.adapters.driven.jpa.mysql.repository.IBootCampRepository;
import com.pragma.OnClass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.OnClass.domain.model.BootCamp;
import com.pragma.OnClass.domain.model.Capacity;
import com.pragma.OnClass.domain.spi.IBootCampPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BootCampAdapter implements IBootCampPersistencePort {
    private final IBootCampRepository bootCampRepository;

    private final IBootCampEntityMapper bootCampEntityMapper;

    private final ICapacityRepository capacityRepository;


    @Override
    public void saveBootCamp(BootCamp bootCamp) {
        String normalizedBootCampName = bootCamp.getName().toLowerCase();
        bootCamp.setName(normalizedBootCampName);

        if (bootCamp.getCapacities() != null && !bootCamp.getCapacities().isEmpty()) {
            List<CapacityEntity> capacityEntities = new ArrayList<>();
            for (Capacity capacity : bootCamp.getCapacities()) {
                Optional<CapacityEntity> existingCapacity = capacityRepository.findById(capacity.getId());
                if (existingCapacity.isPresent()) {
                    Long capacityId = existingCapacity.get().getId();
                    if (capacityEntities.stream().anyMatch(c -> c.getId().equals(capacityId))) {
                        throw new DuplicateCapacityException();
                    }
                    capacityEntities.add(existingCapacity.get());
                } else {
                    throw new CapacityNotFoundException();
                }
            }
            BootCampEntity bootCampEntity = bootCampEntityMapper.toEntity(bootCamp);
            bootCampEntity.setCapacities(capacityEntities);
            bootCampRepository.save(bootCampEntity);
        }

    }



}
