package com.learning.microservice.mywallet.exception;

import java.security.PublicKey;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
