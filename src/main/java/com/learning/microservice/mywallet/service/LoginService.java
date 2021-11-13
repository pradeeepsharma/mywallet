package com.learning.microservice.mywallet.service;

import com.learning.microservice.mywallet.exception.CustomerNotFoundException;
import com.learning.microservice.mywallet.repository.LoginInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginInfoRepository repository;


    public Boolean createLogin(Long customerId, String password) throws CustomerNotFoundException {
        Boolean loginCreated = false;
        repository.findById(customerId).orElseThrow(()->new CustomerNotFoundException(env.getProperty("customer.not.present") +":"+customerId));

        return loginCreated;
    }
}
