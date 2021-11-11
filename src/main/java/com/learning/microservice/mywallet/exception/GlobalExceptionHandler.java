package com.learning.microservice.mywallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobelExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomerAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> duplicateCustomerHandler(CustomerAlreadyExistException exception) {
        return new ResponseEntity<>("enter a valid email", HttpStatus.BAD_REQUEST);
    }
}
