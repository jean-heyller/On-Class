package com.pragma.onclass.adapters.driven.jpa.mysql.adapter;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.onclass.utils.exceptions.IncompatibleValueException;
import com.pragma.onclass.utils.exceptions.NoDataFoundException;
import com.pragma.onclass.utils.exceptions.TechnologyAlreadyExitsException;
import com.pragma.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.pragma.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;
    @Override
    public void saveTechnology(Technology technology){
        String normalizedTechnologyName = technology.getName().toLowerCase();
        if(technologyRepository.findByName(normalizedTechnologyName).isPresent()){
            String message = "the Technology";
            throw new TechnologyAlreadyExitsException(message);
        }
        technology.setName(normalizedTechnologyName);
        technologyRepository.save(technologyEntityMapper.toEntity(technology));


    }

    @Override
    public Technology getTechnology(String name) {
        return null;
    }

    @Override
    public List<Technology> getAllTechnologies(Integer page, Integer size, boolean isAsc) {
        if (page < 0 || size < 0) {
            throw new IncompatibleValueException();
        }
       Sort sort = isAsc ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        List<TechnologyEntity> allTechnologies = technologyRepository.findAll(pageable).getContent();
        if (allTechnologies.isEmpty()) {
            throw  new NoDataFoundException();
        }
        return technologyEntityMapper.toModelist(allTechnologies);
    }

}
