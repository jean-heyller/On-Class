package com.pragma.onclass.adapters.driving.http.dto.request;

import com.pragma.onclass.domain.model.Bootcamp;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@Getter
public class AddVersionRequest {

    @NotBlank
    private final Date startDate;

    @NotBlank
    private final Date endDate;

    @NotBlank
    @Size(max = 50,min = 1)
    private final Integer maximumQuota;

    @NotBlank
    private final Long bootcampId;




}
