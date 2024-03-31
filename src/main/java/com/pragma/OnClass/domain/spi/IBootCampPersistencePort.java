package com.pragma.OnClass.domain.spi;

import com.pragma.OnClass.domain.model.BootCamp;

import java.util.List;

public interface IBootCampPersistencePort {
    void saveBootCamp(BootCamp bootCamp);

    public List<BootCamp> getAllBootCamps(Integer page, Integer size, boolean isAscName, boolean isAscCapacity);

}
