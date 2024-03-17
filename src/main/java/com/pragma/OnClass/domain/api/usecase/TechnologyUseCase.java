package com.pragma.OnClass.domain.api.usecase;

import com.pragma.OnClass.domain.api.ITechnologyServicePort;
import com.pragma.OnClass.domain.exception.EmptyFieldException;
import com.pragma.OnClass.domain.exception.NegativeNotAllowedException;
import com.pragma.OnClass.domain.model.Technology;
import com.pragma.OnClass.domain.spi.ITechnologyPersistencePort;
import com.pragma.OnClass.domain.util.DomainConstants;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {
    private ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort){
        this.technologyPersistencePort = technologyPersistencePort;
    }
    @Override
    public void saveTechnology(Technology technology){
        if(technology.getName().trim().isEmpty()){
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if(technology.getName().length()>50){
            throw new NegativeNotAllowedException(DomainConstants.Field.NAME.toString());
        }
        if(technology.getDescription().length() > 90){
            throw new NegativeNotAllowedException(DomainConstants.Field.DESCRIPTION.toString());
        }
        technologyPersistencePort.saveTechnology(technology);}

    @Override
    public Technology getTechnology(String name) {
        return technologyPersistencePort.getTechnology(name);
    }



    @Override
    public List<Technology> getAllTechnology(Integer page, Integer size) {
        return technologyPersistencePort.getAllTechnology(page,size);
    }

}
