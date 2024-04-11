package com.pragma.onclass.adapters.driving.http.mapper;

import com.pragma.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.model.Technology;
import com.pragma.onclass.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface IVersionRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bootcamp.name", constant = "bootcampName")
    @Mapping(target = "bootcamp.description", constant = "bootcampDescription")
    Version addRequestToVersion( AddVersionRequest addVersionRequest);



}
