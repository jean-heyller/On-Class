package com.pragma.onclass.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddVersionRequestTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void whenStartDateIsNull_thenOneConstraintViolation() {
        AddVersionRequest request = new AddVersionRequest(null, LocalDate.now(), 10, 1L);
        Set violations = validator.validate(request);
        assertEquals(1, violations.size());
    }

    @Test
    void whenEndDateIsNull_thenOneConstraintViolation() {
        AddVersionRequest request = new AddVersionRequest(LocalDate.now(), null, 10, 1L);
        Set violations = validator.validate(request);
        assertEquals(1, violations.size());
    }

    @Test
    void whenMaximumQuotaIsNull_thenOneConstraintViolation() {
        AddVersionRequest request = new AddVersionRequest(LocalDate.now(), LocalDate.now(), null, 1L);
        Set violations = validator.validate(request);
        assertEquals(1, violations.size());
    }

    @Test
    void whenBootcampIdIsNull_thenOneConstraintViolation() {
        AddVersionRequest request = new AddVersionRequest(LocalDate.now(), LocalDate.now(), 10, null);
        Set violations = validator.validate(request);
        assertEquals(1, violations.size());
    }

    @Test
    void whenStartDateIsInThePast_thenOneConstraintViolation() {
        AddVersionRequest request = new AddVersionRequest(LocalDate.now().minusDays(1), LocalDate.now(), 10, 1L);
        Set violations = validator.validate(request);
        assertEquals(1, violations.size());
    }

    @Test
    void whenEndDateIsInThePast_thenOneConstraintViolation() {
        AddVersionRequest request = new AddVersionRequest(LocalDate.now(), LocalDate.now().minusDays(1), 10, 1L);
        Set violations = validator.validate(request);
        assertEquals(1, violations.size());
    }

    @Test
    void whenMaximumQuotaIsLessThanOne_thenOneConstraintViolation() {
        AddVersionRequest request = new AddVersionRequest(LocalDate.now(), LocalDate.now(), 0, 1L);
        Set violations = validator.validate(request);
        assertEquals(1, violations.size());
    }
}