package com.pragma.OnClass.domain.api;

import com.pragma.OnClass.domain.model.Technology;

import java.util.List;

public interface ITechnologyServicePort {
    void saveTechnology(Technology technology);
    Technology getTechnology(String name);

    List<Technology> getAllTechnology(Integer page, Integer size, boolean isAsc);
}
