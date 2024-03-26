package com.pragma.OnClass.adapters.driving.http.mapper;

import com.pragma.OnClass.adapters.driving.http.dto.response.BootCampResponse;
import com.pragma.OnClass.domain.model.BootCamp;
import com.pragma.OnClass.domain.model.Capacity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootCampResponseMapper {
    BootCampResponse toBootCampResponse(BootCamp bootCamp);
    List<BootCampResponse> toBootCampResponseList(List<BootCamp> bootCamps);
}
