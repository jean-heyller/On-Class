package com.pragma.onclass.adapters.driving.http.mapper;
import com.pragma.onclass.adapters.driving.http.dto.response.CapacityResponse;
import com.pragma.onclass.domain.model.Capacity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ICapacityResponseMapper {

    CapacityResponse toCapacityResponse(Capacity capacity);

    List<CapacityResponse> toCapacityResponseList(List<Capacity> capacities);

}
