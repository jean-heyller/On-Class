package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.pragma.onclass.domain.model.Capacity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapacityEntityMapper {
    Capacity toModel(CapacityEntity capacityEntity);
    CapacityEntity toEntity(Capacity capacity);

    List<Capacity> toModelist(List<CapacityEntity> capacityEntities );
}
