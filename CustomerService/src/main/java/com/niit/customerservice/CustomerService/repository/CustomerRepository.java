package com.niit.customerservice.CustomerService.repository;

import com.niit.customerservice.CustomerService.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer,Integer> {

    Customer findBycustomerName(String name);

    @Query("{'address.city' : {$in:[?0]}}")

    List<Customer> findAllCustomerByCity(String city);

}
