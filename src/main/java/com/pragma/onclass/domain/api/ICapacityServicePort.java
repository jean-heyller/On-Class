package com.pragma.onclass.domain.api;

import com.pragma.onclass.domain.model.Capacity;

import java.util.List;

public interface ICapacityServicePort {
    void saveCapacity(Capacity capacity);
    public Capacity getCapacity(String name);

    public List<Capacity> getAllCapacities(Integer page, Integer size,boolean isAscName, boolean isAscTechnology);
}
