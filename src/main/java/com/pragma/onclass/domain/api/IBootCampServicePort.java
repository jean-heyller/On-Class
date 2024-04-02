package com.pragma.onclass.domain.api;

import com.pragma.onclass.domain.model.BootCamp;

import java.util.List;

public interface IBootCampServicePort {
    void saveBootCamp(BootCamp bootCamp);

    List<BootCamp> getAllBootCamps(Integer page, Integer size, boolean isAscName, boolean isAscCapacity);
}
