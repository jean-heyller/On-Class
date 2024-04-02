package com.pragma.onclass.adapters.driving.http.dto.request;

import com.pragma.onclass.adapters.driving.http.util.DomainConstants;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AddTechnologyRequestTest {
        private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        private final Validator validator = factory.getValidator();

    @Test
    void testConstructorAndGetters() {
        AddTechnologyRequest addTechnologyRequest = new AddTechnologyRequest("Django", "Programing Language");
        assertEquals("Django", addTechnologyRequest.getName());
        assertEquals("Programing Language", addTechnologyRequest.getDescription());
    }

    @Test
    void testValidationNotBlank() {
        AddTechnologyRequest addTechnologyRequest = new AddTechnologyRequest("", "");
        assertEquals(2, validator.validate(addTechnologyRequest).size());
        for(ConstraintViolation<AddTechnologyRequest> violation : validator.validate(addTechnologyRequest)) {
            assertTrue(violation.getMessage().contains(DomainConstants.FIELD_NAME_NULL_MESSAGE) ||
                    violation.getMessage().contains(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE));
        }
    }

    @Test
    void testValidationSize() {
        String longName = "a".repeat(100);
        String longDescription = "a".repeat(100);
        AddTechnologyRequest addTechnologyRequest = new AddTechnologyRequest(longName, longDescription);
        Set<ConstraintViolation<AddTechnologyRequest>> violations = validator.validate(addTechnologyRequest);
        assertEquals(2, violations.size());
        for (ConstraintViolation<AddTechnologyRequest> violation : violations) {
            assertTrue(violation.getMessage().contains(DomainConstants.FIELD_DESCRIPTION_SIZE_MESSAGE) ||
                    violation.getMessage().contains(DomainConstants.FIELD_NAME_SIZE_MESSAGE));
        }
    }
}