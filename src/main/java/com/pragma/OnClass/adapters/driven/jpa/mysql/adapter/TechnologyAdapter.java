package com.pragma.OnClass.adapters.driven.jpa.mysql.adapter;

import com.pragma.OnClass.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExitsException;
import com.pragma.OnClass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.OnClass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.OnClass.domain.model.Technology;
import com.pragma.OnClass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;
    @Override
    public void saveTechnology(Technology technology){
        String normalizedTechnologyName = technology.getName().toLowerCase();
        if(technologyRepository.findByName(normalizedTechnologyName).isPresent()){
            throw new TechnologyAlreadyExitsException();
        }
        technology.setName(normalizedTechnologyName);
        technologyRepository.save(technologyEntityMapper.toEntity(technology));


    }

    @Override
    public Technology getTechnology(String name) {
        return null;
    }

    @Override
    public List<Technology> getAllTechnology(Integer page, Integer size) {
        return null;
    }

}
