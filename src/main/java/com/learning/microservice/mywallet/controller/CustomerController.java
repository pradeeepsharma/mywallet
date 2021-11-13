package com.learning.microservice.mywallet.controller;

import com.learning.microservice.mywallet.domain.Customer;
import com.learning.microservice.mywallet.exception.CustomerAlreadyExistException;
import com.learning.microservice.mywallet.exception.CustomerNotFoundException;
import com.learning.microservice.mywallet.model.CustomerRequest;
import com.learning.microservice.mywallet.model.CustomerResponse;
import com.learning.microservice.mywallet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private static final Logger logger= LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/customer", produces = " application/json")
    public ResponseEntity<CustomerResponse> addCustomer(@Valid@RequestBody CustomerRequest request)throws CustomerAlreadyExistException {
        logger.debug("request received :"+request);
        Customer customer = customerService.addCustomer(request);
        CustomerResponse response = CustomerResponse.builder().customerId(customer.getId()).build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/customer")
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
    }

    @PostMapping(value = "/customer/{id}/login/{password}")
    public ResponseEntity<Boolean> createLogin(@NotNull @PathVariable Long id,@NotNull @PathVariable String password)throws CustomerNotFoundException {
        return new ResponseEntity<>(customerService.createLogin(id, password), HttpStatus.CREATED);
    }
}
