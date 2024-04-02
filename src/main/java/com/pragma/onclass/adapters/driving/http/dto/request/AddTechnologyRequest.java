package com.pragma.onclass.adapters.driving.http.dto.request;

import com.pragma.onclass.adapters.driving.http.util.DomainConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class AddTechnologyRequest {

    @NotBlank(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(max = 50, message = DomainConstants.FIELD_NAME_SIZE_MESSAGE)
    private  final String name;

    @NotBlank(message = DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    @Size(max = 90, message = DomainConstants.FIELD_DESCRIPTION_SIZE_MESSAGE)
    private final String description;
}
