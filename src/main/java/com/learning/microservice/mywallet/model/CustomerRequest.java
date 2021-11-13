package com.learning.microservice.mywallet.model;

import com.learning.microservice.mywallet.util.MessageUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@RequiredArgsConstructor
public class CustomerRequest {
   // private static final String validEmail = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    @Autowired
    MessageUtil messageUtil;
    @NotNull(message = "is a required field")
    private String firstName;
    @NotNull(message = "is required field")
    private String lastName;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "email should be in proper format")
    private String email;
    private String dob;
    @Pattern(regexp = "^\\d{10}$", message = " should be a 10 digit phone number")
    private String phone;
    private String postCode;
}
