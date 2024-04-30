package com.pragma.onclass.adapters.driving.http.dto.request;

import com.pragma.onclass.adapters.driving.http.util.DomainConstants;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddBootcampRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testConstructorAndGetters() {
        List<Long> capacities = List.of(1L, 2L, 3L);
        AddBootcampRequest addBootCampRequest = new AddBootcampRequest("Django", "Programing Language", capacities);
        assertEquals("Django", addBootCampRequest.getName());
        assertEquals("Programing Language", addBootCampRequest.getDescription());
        assertEquals(capacities, addBootCampRequest.getCapacities());
    }

    @Test
    void testValidationNotBlank() {
        List<Long> capacities = List.of(1L, 2L, 3L);
        AddBootcampRequest addBootCampRequest = new AddBootcampRequest("", "", capacities);
        assertEquals(2, validator.validate(addBootCampRequest).size());
        for(ConstraintViolation<AddBootcampRequest> violation : validator.validate(addBootCampRequest)) {
            assertTrue(violation.getMessage().contains(DomainConstants.FIELD_NAME_NULL_MESSAGE) ||
                    violation.getMessage().contains(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE));
        }

    }

    @Test
    void testValidationMaxSizeCapacities() {
        List<Long> capacities = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L);
        AddBootcampRequest addBootCampRequest = new AddBootcampRequest("Django", "Programing Language", capacities);
        Set<ConstraintViolation<AddBootcampRequest>> violations = validator.validate(addBootCampRequest);
        assertEquals(1, violations.size());
        for (ConstraintViolation<AddBootcampRequest> violation : violations) {
            assertTrue(violation.getMessage().contains(DomainConstants.FIELD_CAPACITIES_SIZE_MESSAGE));
        }
    }
    

}