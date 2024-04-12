package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.onclass.utils.exceptions.*;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;


@RequiredArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {
    public static final String CAPACITY_EXISTS_ERROR_MESSAGE = "The capacity";
    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;

    private final ITechnologyRepository technologyRepository;

    @Override
    public void saveCapacity(Capacity capacity) {

        String normalizedCapName = capacity.getName().toLowerCase();
        capacity.setName(normalizedCapName);

        if (capacityRepository.findByName(normalizedCapName).isPresent()){
            throw new ValueAlreadyExitsException(CAPACITY_EXISTS_ERROR_MESSAGE);
        }

        if (capacity.getTechnologies() != null && !capacity.getTechnologies().isEmpty()) {
            List<TechnologyEntity> technologyEntities = new ArrayList<>();

            for (Technology technology : capacity.getTechnologies()) {
                Optional<TechnologyEntity> existingTechnology = technologyRepository.findById(technology.getId());

                if (existingTechnology.isPresent()) {
                    technologyEntities.add(existingTechnology.get());

                } else {
                    throw new TechnologyNotFoundException();
                }
            }

            CapacityEntity capacityEntity = capacityEntityMapper.toEntity(capacity);
            capacityEntity.setTechnologies(technologyEntities);
            capacityRepository.save(capacityEntity);
        }
    }
    @Override
    public Capacity getCapacity(String name) {
        return capacityEntityMapper.toModel(capacityRepository.findByName(name).orElseThrow());
    }

    @Override
    public List<Capacity> getAllCapacities(Integer page, Integer size, boolean isAscName, boolean isAscTechnology) {
        Pageable pagination = PageRequest.of(page, size);

        List<CapacityEntity> capacities = capacityRepository.findAll(pagination).getContent();

        if (capacities.isEmpty()) {
            throw new NoDataFoundException();
        }

        List<CapacityEntity> capacityEntities = new ArrayList<>(capacities);

        Comparator<CapacityEntity> comparator = Comparator.comparing(CapacityEntity::getName);
        if (!isAscName) {
            comparator = comparator.reversed();
        }

        comparator = comparator.thenComparingInt(e -> e.getTechnologies().size());
        if (!isAscTechnology) {
            comparator = comparator.reversed();
        }

        capacityEntities.sort(comparator);

        return capacityEntityMapper.toModelist(capacityEntities);
    }



}
