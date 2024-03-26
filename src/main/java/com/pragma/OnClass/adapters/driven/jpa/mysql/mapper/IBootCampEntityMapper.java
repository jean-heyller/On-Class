package com.pragma.OnClass.adapters.driven.jpa.mysql.mapper;

import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.BootCampEntity;
import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.OnClass.domain.model.BootCamp;
import com.pragma.OnClass.domain.model.Capacity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootCampEntityMapper {
    BootCamp toModel(BootCampEntity bootCampEntity);
    BootCampEntity toEntity(BootCamp bootCamp);
}
