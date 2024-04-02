package com.pragma.onclass.adapters.driving.http.mapper;

import com.pragma.onclass.adapters.driving.http.dto.request.AddBootCampRequest;
import com.pragma.onclass.domain.model.BootCamp;
import com.pragma.onclass.domain.model.Capacity;
import com.pragma.onclass.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootCampRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "capacities",expression = "java(mapCapacities(bootCampRequest.getCapacities()))")
    BootCamp addRequestToBootCamp(AddBootCampRequest bootCampRequest);

    default List<Capacity> mapCapacities(List<Long> capacities) {
        List<Capacity> mappedCapacities = new ArrayList<>();
        for (Long capacityId : capacities) {
            String name = "name";
            String description = "description";
            List<Technology> technologies = new ArrayList<>();
            technologies.add(new Technology(1L, "Technology 1", "Description 1"));
            technologies.add(new Technology(2L, "Technology 2", "Description 2"));
            technologies.add(new Technology(3L, "Technology 3", "Description 3"));
            Capacity mappedCapacity = new Capacity(capacityId, name, description, technologies);
            mappedCapacities.add(mappedCapacity);
        }
        return mappedCapacities;
    }

}
