package com.pragma.onclass.adapters.driving.http.mapper;


import com.pragma.onclass.adapters.driving.http.dto.response.VersionResponse;

import com.pragma.onclass.domain.model.Version;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionResponseMapper {


    VersionResponse toVersionResponse(Version version);


    List<VersionResponse> toVersionResponseList(List<Version> versions);
}
