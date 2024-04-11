package com.pragma.onclass.adapters.driving.http.mapper;

import com.pragma.onclass.adapters.driving.http.dto.response.BootCampResponse;
import com.pragma.onclass.domain.model.Bootcamp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampResponseMapper {
    BootCampResponse toBootCampResponse(Bootcamp bootCamp);
    List<BootCampResponse> toBootCampResponseList(List<Bootcamp> bootcamps);
}
