package com.pragma.OnClass.domain.api;

import com.pragma.OnClass.domain.model.Capacity;

import java.util.List;

public interface ICapacityServicePort {
    void saveCapacity(Capacity capacity);
    public Capacity getCapacity(String name);

    public List<Capacity> getAllCapacities(Integer page, Integer size,boolean isAscName, boolean isAscTechnology);
}
