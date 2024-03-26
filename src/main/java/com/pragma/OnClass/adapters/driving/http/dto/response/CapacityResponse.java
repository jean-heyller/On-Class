package com.pragma.OnClass.adapters.driving.http.dto.response;
import com.pragma.OnClass.adapters.driving.http.dto.TypeDtoTechnology;
import com.pragma.OnClass.domain.model.Technology;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.List;


@AllArgsConstructor
@Getter
public class CapacityResponse {
    private final Long id;
    private final String name;
    private final String description;

    private final List<TypeDtoTechnology> technologies;





}
