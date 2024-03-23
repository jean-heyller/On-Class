package com.pragma.OnClass.adapters.driven.jpa.mysql.adapter;

import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.OnClass.adapters.driven.jpa.mysql.exception.DuplicateTechnologyException;
import com.pragma.OnClass.adapters.driven.jpa.mysql.exception.TechnologyNotFoundException;
import com.pragma.OnClass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.pragma.OnClass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.pragma.OnClass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.OnClass.domain.model.Capacity;
import com.pragma.OnClass.domain.model.Technology;
import com.pragma.OnClass.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {
    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;

    private final ITechnologyRepository technologyRepository;

    @Override
    public void saveCapacity(Capacity capacity) {

        String normalizedCapName = capacity.getName().toLowerCase();
        capacity.setName(normalizedCapName);

        if (capacity.getTechnologies() != null && !capacity.getTechnologies().isEmpty()) {
            List<TechnologyEntity> technologyEntities = new ArrayList<>();

            for (Technology technology : capacity.getTechnologies()) {
                Optional<TechnologyEntity> existingTechnology = technologyRepository.findById(technology.getId());

                if (existingTechnology.isPresent()) {
                    Long technologyId = existingTechnology.get().getId();

                    if (technologyEntities.stream().anyMatch(t -> t.getId().equals(technologyId))) {
                        throw new DuplicateTechnologyException();
                    }

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

}
