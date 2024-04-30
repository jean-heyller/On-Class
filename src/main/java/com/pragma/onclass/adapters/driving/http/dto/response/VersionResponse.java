package com.pragma.onclass.adapters.driving.http.dto.response;

import com.pragma.onclass.adapters.driving.http.dto.TypeDtoBootcamp;
import com.pragma.onclass.domain.model.Bootcamp;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class VersionResponse {
    private final LocalDate startDate;

    private final LocalDate endDate;


    private final Integer maximumQuota;

    private final TypeDtoBootcamp bootcamp;
}
