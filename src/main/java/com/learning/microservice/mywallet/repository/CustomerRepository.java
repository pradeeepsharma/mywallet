package com.learning.microservice.mywallet.repository;

import com.learning.microservice.mywallet.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Customer findByEmail(String email);
}
