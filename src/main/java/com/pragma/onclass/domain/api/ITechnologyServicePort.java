package com.pragma.onclass.domain.api;

import com.pragma.onclass.domain.model.Technology;

import java.util.List;

public interface ITechnologyServicePort {
    void saveTechnology(Technology technology);
    Technology getTechnology(String name);

    List<Technology> getAllTechnologies(Integer page, Integer size, boolean isAsc);
}
