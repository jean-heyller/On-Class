package com.pragma.onclass.adapters.driving.http.mapper;

import com.pragma.onclass.adapters.driving.http.dto.response.BootCampResponse;
import com.pragma.onclass.domain.model.BootCamp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootCampResponseMapper {
    BootCampResponse toBootCampResponse(BootCamp bootCamp);
    List<BootCampResponse> toBootCampResponseList(List<BootCamp> bootCamps);
}
