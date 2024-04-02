package com.pragma.onclass.domain.api.usecase;

import com.pragma.onclass.domain.api.ITechnologyServicePort;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.spi.ITechnologyPersistencePort;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {
    private ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort){
        this.technologyPersistencePort = technologyPersistencePort;
    }
    @Override
    public void saveTechnology(Technology technology){
        technologyPersistencePort.saveTechnology(technology);}

    @Override
    public Technology getTechnology(String name) {
        return technologyPersistencePort.getTechnology(name);
    }



    @Override
    public List<Technology> getAllTechnologies(Integer page, Integer size, boolean isAsc) {
        return technologyPersistencePort.getAllTechnologies(page,size,isAsc);
    }

}
