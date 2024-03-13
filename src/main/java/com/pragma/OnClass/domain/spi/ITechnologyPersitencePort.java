package com.pragma.OnClass.domain.spi;

import com.pragma.OnClass.domain.model.Technology;

import java.util.List;

public interface ITechnologyPersitencePort {
    void saveTechnology(Technology technology);
    Technology getTechnology(String name);

    List<Technology> getAllTechnology(Integer page, Integer size);
}
