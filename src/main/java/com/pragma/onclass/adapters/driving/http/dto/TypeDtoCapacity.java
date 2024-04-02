package com.pragma.onclass.adapters.driving.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TypeDtoCapacity {
    private final Long id;

    private final String name;

    private final List<TypeDtoTechnology> technologies;
}
