package com.pragma.OnClass.configuration.exceptionhandler;

import com.pragma.OnClass.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExitsException;
import com.pragma.OnClass.configuration.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor

public class ControllerAdvisor {

    @ExceptionHandler(TechnologyAlreadyExitsException.class)
    public ResponseEntity<ExceptionResponse> handleSupplierAlreadyExistsException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.TECHNOLOGY_ALREADY_EXITS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

}
