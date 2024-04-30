package com.pragma.onclass.domain.spi;

import com.pragma.onclass.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampPersistencePort {
    void saveBootCamp(Bootcamp bootCamp);

    public List<Bootcamp> getAllBootCamps(Integer page, Integer size, boolean isAscName, boolean isAscCapacity);

}
