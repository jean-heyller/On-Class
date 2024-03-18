package com.pragma.OnClass.configuration.exceptionhandler;

import com.pragma.OnClass.adapters.driven.jpa.mysql.exception.IncompatibleValueException;
import com.pragma.OnClass.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma.OnClass.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExitsException;

import com.pragma.OnClass.configuration.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;


import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor

public class ControllerAdvisor {
    private String getErrorMessage(FieldError error) {
        if (error == null) {
            return "Validation error";
        }
        String code = error.getCode();
        switch (code != null ? code : "") {
            case "NotBlank":
                return Constants.EMPTY_FIELD_EXCEPTION_MESSAGE;
            case "Size":
                return Constants.MAX_CHAR_EXCEPTION_MESSAGE;
            default:
                return error.getDefaultMessage();
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        String errorMessage = getErrorMessage(error);
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                errorMessage, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(TechnologyAlreadyExitsException.class)
    public ResponseEntity<ExceptionResponse> handleSupplierAlreadyExistsException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.TECHNOLOGY_ALREADY_EXITS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(IncompatibleValueException.class)
    public ResponseEntity<ExceptionResponse> handleIncompatibleValueException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.INCOMPATIBLE_VALUE_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

}
