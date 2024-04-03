package com.pragma.onclass.adapters.driving.http.mapper;

import com.pragma.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.pragma.onclass.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IVersionRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bootcamp.name", source = "bootcampName")
    @Mapping(target = "bootcamp.id", source = "bootcampId" )
    @Mapping(target = "bootcamp.description", source = "bootcampDescription")
    @Mapping(target = "bootcamp.capacities", source = "bootcampCapacities",defaultValue = "java.util.Collections.emptyList()")
    Version addRequestToVersion( AddVersionRequest addVersionRequest);
}
