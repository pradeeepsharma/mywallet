package com.learning.microservice.mywallet.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.learning.microservice.mywallet.domain.Customer;
import com.learning.microservice.mywallet.exception.CustomerAlreadyExistException;
import com.learning.microservice.mywallet.exception.InvalidInputException;
import com.learning.microservice.mywallet.model.CustomerRequest;
import com.learning.microservice.mywallet.repository.CustomerRepository;
import com.learning.microservice.mywallet.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private WalletService walletService;

    @Autowired
    private MessageUtil env;

    private Cache<Long, Customer> getCustomerCache;

    public Customer addCustomer(CustomerRequest request) {

        if (nonNull(repository.findByEmail(buildCustomerFromRequest(request).getEmail())))
            throw new CustomerAlreadyExistException("User already exist with email:" + buildCustomerFromRequest(request).getEmail());
        Customer savedCustomer = repository.save(buildCustomerFromRequest(request));
        walletService.createWallet(savedCustomer.getId());
        return savedCustomer;
    }

    private Customer buildCustomerFromRequest(CustomerRequest request) {
        Customer customerToAdd;
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
        return customerToAdd;
    }

    public List<Customer> getAllCustomers() {
        Iterable<Customer> customerIterable = repository.findAll();
        List<Customer> customers = new ArrayList<>();
        customerIterable.forEach(customer ->{
            getCustomerCache.put(customer.getId(),customer);
            customers.add(customer);
        });
        return customers;
    }

    public Customer getCustomer(Long id) {
        Customer customerFromCache = getCustomerCache.getIfPresent(id);
        return getAllCustomers().stream().filter(customer->customer.getId().equals(id)).findFirst().orElseThrow(()->new CustomerAlreadyExistException("no customer found"));
    }

    public Customer getCustomer(String email) {
        return getAllCustomers().stream().filter(customer->customer.getEmail().equals(email)).findFirst().orElseThrow(()->new CustomerAlreadyExistException("no customer found"));
    }
}
