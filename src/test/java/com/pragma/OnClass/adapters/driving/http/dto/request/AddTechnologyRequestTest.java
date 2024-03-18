package com.pragma.OnClass.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AddTechnologyRequestTest {
        private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        private final Validator validator = factory.getValidator();

    @Test
    void testConstrutorAndGetters() {
        AddTechnologyRequest addTechnologyRequest = new AddTechnologyRequest("Django", "Programing Language");
        assertEquals("Django", addTechnologyRequest.getName());
        assertEquals("Programing Language", addTechnologyRequest.getDescription());
    }

    @Test
    void getDescription() {
    }
}