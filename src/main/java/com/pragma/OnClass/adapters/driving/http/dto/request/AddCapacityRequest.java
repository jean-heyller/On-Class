package com.pragma.OnClass.adapters.driving.http.dto.request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pragma.OnClass.adapters.driving.http.util.DomainConstants;
import com.pragma.OnClass.domain.model.Technology;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Getter
public class AddCapacityRequest {

        @NotBlank(message = DomainConstants.FIELD_NAME_NULL_MESSAGE)
        @Size(max = 50, message = DomainConstants.FIELD_NAME_SIZE_MESSAGE)
        private  final String name;

        @NotBlank(message = DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
        @Size(max = 90, message = DomainConstants.FIELD_DESCRIPTION_SIZE_MESSAGE)
        private final String description;


        private final List<Long> technologies;

}
