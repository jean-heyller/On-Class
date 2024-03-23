package com.pragma.OnClass.adapters.driving.http.mapper;
import com.pragma.OnClass.adapters.driving.http.dto.request.AddCapacityRequest;

import com.pragma.OnClass.domain.model.Capacity;
import com.pragma.OnClass.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")

public interface ICapacityRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "technologies", expression = "java(mapTechnologies(addCapacityRequest.getTechnologies()))")
    Capacity addRequestToCapacity(AddCapacityRequest addCapacityRequest);



    default List<Technology> mapTechnologies(List<Long> technologies) {
        List<Technology> mappedTechnologies = new ArrayList<>();
        for (Long techId : technologies) {
            String name = "name";
            String description = "description";
            Technology mappedTech = new Technology(techId, name, description);
            mappedTechnologies.add(mappedTech);
        }
        return mappedTechnologies;
    }


}
