package com.pragma.onclass.adapters.driving.http.dto.response;
import com.pragma.onclass.adapters.driving.http.dto.TypeDtoTechnology;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@Getter
public class CapacityResponse {
    private final Long id;
    private final String name;
    private final String description;

    private final List<TypeDtoTechnology> technologies;





}
