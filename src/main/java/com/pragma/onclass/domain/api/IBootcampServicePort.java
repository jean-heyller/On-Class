package com.pragma.onclass.domain.api;

import com.pragma.onclass.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampServicePort {
    void saveBootCamp(Bootcamp bootCamp);

    List<Bootcamp> getAllBootCamps(Integer page, Integer size, boolean isAscName, boolean isAscCapacity);
}
