package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootCampEntity;

import com.pragma.onclass.domain.model.BootCamp;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IBootCampEntityMapper {
    BootCamp toModel(BootCampEntity bootCampEntity);
    BootCampEntity toEntity(BootCamp bootCamp);

    List<BootCamp> toModelList(List<BootCampEntity> bootCampEntities);
}
