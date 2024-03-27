package com.pragma.OnClass.adapters.driving.http.dto.request;

import com.pragma.OnClass.adapters.driving.http.util.DomainConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@AllArgsConstructor
@Getter
public class AddBootCampRequest {

            @NotBlank(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
            @Size(max = 50, message = DomainConstants.FIELD_NAME_SIZE_MESSAGE)
            private  final String name;

            @NotBlank(message = DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
            @Size(max = 90, message = DomainConstants.FIELD_DESCRIPTION_SIZE_MESSAGE)
            private final String description;

            @NotEmpty(message = DomainConstants.FIELD_CAPACITIES_NULL_MESSAGE)
            @Size(min = 1,max = 4, message = DomainConstants.FIELD_CAPACITIES_SIZE_MESSAGE)
            private final List<Long> capacities;

}
