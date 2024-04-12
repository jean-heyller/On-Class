package com.pragma.onclass.adapters.driving.http.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@AllArgsConstructor
@Getter
public class AddVersionRequest {

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be in the present or future")
    private final LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @FutureOrPresent(message = "End date must be in the present or future")
    private final LocalDate endDate;

    @NotNull(message = "Maximum quota cannot be null")
    @Min(value = 1, message = "Maximum quota must be greater than 0")
    private final Integer maximumQuota;

    @NotNull(message = "Bootcamp ID cannot be null")
    private final Long bootcampId;
}
