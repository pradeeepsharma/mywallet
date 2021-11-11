package com.learning.microservice.mywallet.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private Integer phone;
    private String postCode;
    private Double balance;

}
