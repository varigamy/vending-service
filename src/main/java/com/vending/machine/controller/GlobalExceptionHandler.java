package com.vending.machine.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vending.machine.exception.InsufficientCoinageException;
import com.vending.machine.exception.OperationProcessingException;

/**
 * Provides handling of the Application exceptions with converting them to appropriate 
 * {@link HttpStatus} codes that can be correctly recognized by API consumers.
 * 
 * @author a.zherdetski
 *
 */
@ControllerAdvice @RequestMapping(produces = "application/vnd.error+json")
public class GlobalExceptionHandler {
	
    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException e) {
         Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
         StringBuilder strBuilder = new StringBuilder();
         for (ConstraintViolation<?> violation : violations ) {
              strBuilder.append(violation.getMessage() + "\n");
         }
         return strBuilder.toString();
    }

    @ExceptionHandler(value = { OperationProcessingException.class, InsufficientCoinageException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleProcessingException(Exception e) {
         return e.getMessage();
    }
}
