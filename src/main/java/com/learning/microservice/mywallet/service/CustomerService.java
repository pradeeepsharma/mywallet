package com.learning.microservice.mywallet.service;

import ch.qos.logback.core.Context;
import com.learning.microservice.mywallet.domain.Customer;
import com.learning.microservice.mywallet.exception.CustomerAlreadyExistException;
import com.learning.microservice.mywallet.exception.CustomerNotFoundException;
import com.learning.microservice.mywallet.exception.InvalidInputException;
import com.learning.microservice.mywallet.model.CustomerRequest;
import com.learning.microservice.mywallet.repository.CustomerRepository;
import com.learning.microservice.mywallet.repository.LoginInfoRepository;
import com.learning.microservice.mywallet.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private MessageUtil env;

    public Customer addCustomer(CustomerRequest request) {

        Customer customerToAdd = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(env.getProperty("dob.format"));
        try {
            customerToAdd = Customer.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .dob(dateFormat.parse(request.getDob()))
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .postCode(request.getPostCode())
                    .build();
        } catch (ParseException e) {
            throw new InvalidInputException(env.getProperty("invalid.dob"));
        }
        if(nonNull(repository.findByEmail(customerToAdd.getEmail())))
           throw new CustomerAlreadyExistException("User already exist with email:"+customerToAdd.getEmail());
        Customer savedCustomer = repository.save(customerToAdd);
        return savedCustomer;
    }

    public List<Customer> getAllCustomers() {
        Iterable<Customer> customerIterable = repository.findAll();
        List<Customer> customers = new ArrayList<>();
        customerIterable.forEach(customers::add);
        return customers;
    }
}
