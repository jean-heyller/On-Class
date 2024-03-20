package com.pragma.OnClass.domain.spi;

import com.pragma.OnClass.domain.model.Capacity;

public interface ICapacityPersistencePort {
    void saveCapacity(Capacity capacity);
    Capacity getCapacity(String name);


}
