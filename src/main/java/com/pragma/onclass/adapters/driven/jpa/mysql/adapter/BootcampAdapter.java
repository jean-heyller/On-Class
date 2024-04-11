package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.onclass.domain.model.Bootcamp;
import com.pragma.onclass.utils.exceptions.BootCampAlreadyExitsException;


import com.pragma.onclass.utils.exceptions.NoDataFoundException;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.IBootCampEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BootcampAdapter implements IBootcampPersistencePort {
    private final IBootcampRepository bootCampRepository;

    private final IBootCampEntityMapper bootCampEntityMapper;

    private final ICapacityRepository capacityRepository;


    @Override
    public void saveBootCamp(Bootcamp bootCamp) {
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
            BootcampEntity bootCampEntity = bootCampEntityMapper.toEntity(bootCamp);
            bootCampEntity.setCapacities(capacityEntities);
            bootCampRepository.save(bootCampEntity);
        }

    }

    @Override
    public List<Bootcamp> getAllBootCamps(Integer page, Integer size, boolean isAscName, boolean isAscCapacity) {
        Pageable pageable = PageRequest.of(page, size);
        List<BootcampEntity> allBootCamps = bootCampRepository.findAll(pageable).getContent();

        if (allBootCamps.isEmpty()) {
            throw new NoDataFoundException();
        }



        List<BootcampEntity> bootCampEntities = new ArrayList<>(allBootCamps);

        Comparator<BootcampEntity> comparator = Comparator.comparing(BootcampEntity::getName);

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
