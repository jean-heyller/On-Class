package com.pragma.OnClass.domain.api.usecase;

import com.pragma.OnClass.domain.api.ITechnologyServicePort;
import com.pragma.OnClass.domain.model.Technology;
import com.pragma.OnClass.domain.spi.ITechnologyPersitencePort;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {
    private ITechnologyPersitencePort technologyPersitencePort;

    public TechnologyUseCase(ITechnologyPersitencePort iTechnologyPersitencePort){
        this.technologyPersitencePort = technologyPersitencePort;
    }
    @Override
    public void saveTechnology(Technology technology){technologyPersitencePort.saveTechnology(technology);}

    @Override
    public Technology getTechnology(String name) {
        return technologyPersitencePort.getTechnology(name);
    }



    @Override
    public List<Technology> getAllTechnology(Integer page, Integer size) {
        return technologyPersitencePort.getAllTechnology(page,size);
    }

}
