package com.learning.microservice.mywallet.exception;

public class CustomerAlreadyExistException extends RuntimeException {
    public CustomerAlreadyExistException(String message){
        super(message);
    }
}
