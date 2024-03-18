package com.pragma.OnClass.domain.api.usecase;

import com.pragma.OnClass.domain.api.ITechnologyServicePort;
import com.pragma.OnClass.domain.model.Technology;
import com.pragma.OnClass.domain.spi.ITechnologyPersistencePort;

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
    public List<Technology> getAllTechnology(Integer page, Integer size, boolean isAsc) {
        return technologyPersistencePort.getAllTechnology(page,size,isAsc);
    }

}
