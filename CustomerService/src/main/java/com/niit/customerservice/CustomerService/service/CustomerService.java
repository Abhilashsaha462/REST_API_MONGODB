package com.niit.customerservice.CustomerService.service;

import com.niit.customerservice.CustomerService.domain.Customer;
import com.niit.customerservice.CustomerService.exception.CustomerAlreadyExists;
import com.niit.customerservice.CustomerService.exception.CustomerNotFound;

import java.util.List;

public interface CustomerService {
    Customer saveCustomerDetail(Customer customer) throws CustomerAlreadyExists;
    boolean deleteCustomer(int id) throws CustomerNotFound;
    List<Customer> getAllCustomerDetail();

    Customer getCustomerDetailByName(String customerName);
    List<Customer> getAllCustomersByCity(String city);
}
