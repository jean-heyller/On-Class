package com.pragma.OnClass.domain.spi;

import com.pragma.OnClass.domain.model.Capacity;

import java.util.List;

public interface ICapacityPersistencePort {
    void saveCapacity(Capacity capacity);
    Capacity getCapacity(String name);

    public List<Capacity> getAllCapacities(Integer page, Integer size, boolean isAscName, boolean isAscTechnology);


}
