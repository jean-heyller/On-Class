package com.pragma.OnClass.domain.api;

import com.pragma.OnClass.domain.model.Capacity;

public interface ICapacityServicePort {
    void saveCapacity(Capacity capacity);
    public Capacity getCapacity(String name);
}
