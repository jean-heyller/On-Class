package com.pragma.OnClass.domain.api;

import com.pragma.OnClass.domain.model.BootCamp;

import java.util.List;

public interface IBootCampServicePort {
    void saveBootCamp(BootCamp bootCamp);

    List<BootCamp> getAllBootCamps(Integer page, Integer size, boolean isAscName, boolean isAscCapacity);
}
