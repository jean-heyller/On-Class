package com.pragma.onclass.adapters.driving.http.dto.request;

import com.pragma.onclass.adapters.driving.http.util.DomainConstants;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddCapacityRequestTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    @Test
    void testConstructorAndGetters() {
        List<Long> technologies = List.of(1L, 2L, 3L);
        AddCapacityRequest addCapacityRequest = new AddCapacityRequest("Django", "Programing Language", technologies);
        assertEquals("Django", addCapacityRequest.getName());
        assertEquals("Programing Language", addCapacityRequest.getDescription());
        assertEquals(technologies, addCapacityRequest.getTechnologies());
    }

    @Test
    void testValidationNotBlank() {
        List<Long> technologies = List.of(1L, 2L, 3L);
        AddCapacityRequest addCapacityRequest = new AddCapacityRequest("", "", technologies);
        assertEquals(2, validator.validate(addCapacityRequest).size());
        for(ConstraintViolation<AddCapacityRequest> violation : validator.validate(addCapacityRequest)) {
            assertTrue(violation.getMessage().contains(DomainConstants.FIELD_NAME_NULL_MESSAGE) ||
                    violation.getMessage().contains(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE));
        }
    }

    @Test
    void testValidationMaxSizeTechnologies() {
        List<Long> technologies = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L);
        AddCapacityRequest addCapacityRequest = new AddCapacityRequest("Django", "Programing Language", technologies);
        Set<ConstraintViolation<AddCapacityRequest>> violations = validator.validate(addCapacityRequest);
        assertEquals(1, violations.size());
        for (ConstraintViolation<AddCapacityRequest> violation : violations) {
            assertTrue(violation.getMessage().contains(DomainConstants.FIELD_TECHNOLOGIES_SIZE_MESSAGE));
        }
    }


    @Test
    void testValidationEmptyTechnologies() {
        List<Long> technologies = List.of();
        AddCapacityRequest addCapacityRequest = new AddCapacityRequest("Django", "Programing Language", technologies);
        Set<ConstraintViolation<AddCapacityRequest>> violations = validator.validate(addCapacityRequest);
        assertEquals(2, violations.size());
        for (ConstraintViolation<AddCapacityRequest> violation : violations) {
            assertTrue(violation.getMessage().contains(DomainConstants.FIELD_TECHNOLOGIES_EMPTY_MESSAGE) ||
                    violation.getMessage().contains(DomainConstants.FIELD_TECHNOLOGIES_SIZE_MESSAGE));
        }

    }
}