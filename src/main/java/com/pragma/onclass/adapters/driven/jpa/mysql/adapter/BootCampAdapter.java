package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootCampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.onclass.utils.exceptions.BootCampAlreadyExitsException;


import com.pragma.onclass.utils.exceptions.NoDataFoundException;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootCampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootCampRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.onclass.domain.model.BootCamp;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.spi.IBootCampPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Comparator;
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

        if (bootCampRepository.findByName(normalizedBootCampName).isPresent()) {
            throw new BootCampAlreadyExitsException();
        }
        bootCamp.setName(normalizedBootCampName);

        if (bootCamp.getCapacities() != null && !bootCamp.getCapacities().isEmpty()) {
            List<CapacityEntity> capacityEntities = new ArrayList<>();
            for (Capacity capacity : bootCamp.getCapacities()) {
                Optional<CapacityEntity> existingCapacity = capacityRepository.findById(capacity.getId());
                if (existingCapacity.isPresent()) {
                    capacityEntities.add(existingCapacity.get());
                } else {
                    throw new NoDataFoundException();
                }
            }
            BootCampEntity bootCampEntity = bootCampEntityMapper.toEntity(bootCamp);
            bootCampEntity.setCapacities(capacityEntities);
            bootCampRepository.save(bootCampEntity);
        }

    }

    @Override
    public List<BootCamp> getAllBootCamps(Integer page, Integer size, boolean isAscName, boolean isAscCapacity) {
        Pageable pageable = PageRequest.of(page, size);
        List<BootCampEntity> allBootCamps = bootCampRepository.findAll(pageable).getContent();

        if (allBootCamps.isEmpty()) {
            throw new NoDataFoundException();
        }



        List<BootCampEntity> bootCampEntities = new ArrayList<>(allBootCamps);

        Comparator<BootCampEntity> comparator = Comparator.comparing(BootCampEntity::getName);

        if (!isAscName) {
            comparator = comparator.reversed();
        }

        comparator = comparator.thenComparing(e-> e.getCapacities().size());

        if (!isAscCapacity) {
            comparator = comparator.reversed();
        }
        bootCampEntities.sort(comparator);

        return bootCampEntityMapper.toModelList(bootCampEntities);





    }



}
