package com.pragma.OnClass.adapters.driven.jpa.mysql.mapper;

import com.pragma.OnClass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.pragma.OnClass.domain.model.Technology;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyEntityMapper {
    Technology toModel(TechnologyEntity technologyEntity);
    TechnologyEntity toEntity(Technology technology);
    List<Technology> toModelist(List<TechnologyEntity> technologyEntities);
}
