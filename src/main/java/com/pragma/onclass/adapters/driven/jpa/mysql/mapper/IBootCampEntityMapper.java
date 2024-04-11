package com.pragma.onclass.adapters.driven.jpa.mysql.mapper;

import com.pragma.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;

import com.pragma.onclass.domain.model.Bootcamp;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IBootCampEntityMapper {
    Bootcamp toModel(BootcampEntity bootCampEntity);
    BootcampEntity toEntity(Bootcamp bootCamp);

    List<Bootcamp> toModelList(List<BootcampEntity> bootCampEntities);
}
