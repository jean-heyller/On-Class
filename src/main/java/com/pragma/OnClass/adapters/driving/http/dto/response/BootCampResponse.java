package com.pragma.OnClass.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BootCampResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final List<CapacityResponse> capacities;

}
