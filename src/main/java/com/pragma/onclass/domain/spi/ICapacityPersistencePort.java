package com.pragma.onclass.domain.spi;

import com.pragma.onclass.domain.model.Capacity;

import java.util.List;

public interface ICapacityPersistencePort {
    void saveCapacity(Capacity capacity);
    Capacity getCapacity(String name);

    public List<Capacity> getAllCapacities(Integer page, Integer size, boolean isAscName, boolean isAscTechnology);


}
