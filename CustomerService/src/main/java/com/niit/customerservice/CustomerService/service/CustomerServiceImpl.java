package com.niit.customerservice.CustomerService.service;

import com.niit.customerservice.CustomerService.domain.Customer;
import com.niit.customerservice.CustomerService.repository.CustomerRepository;
import com.niit.customerservice.CustomerService.exception.CustomerAlreadyExists;
import com.niit.customerservice.CustomerService.exception.CustomerNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository)
    {
        this.customerRepository=customerRepository;
    }


    @Override
    public Customer saveCustomerDetail(Customer customer) throws CustomerAlreadyExists {
        if (customerRepository.findById(customer.getCustomerId()).isPresent())
        {
            throw new CustomerAlreadyExists();
        }
        return customerRepository.save(customer);
    }

    @Override
    public boolean deleteCustomer(int customerCode) throws CustomerNotFound {
        boolean flag=false;
        if (customerRepository.findById(customerCode).isEmpty())
        {
            throw new CustomerNotFound();
        }
        else
        {
            customerRepository.deleteById(customerCode);
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Customer> getAllCustomerDetail() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerDetailByName(String customerName) {
        return customerRepository.findBycustomerName(customerName);
    }

    @Override
    public List<Customer> getAllCustomersByCity(String city) {
        return customerRepository.findAllCustomerByCity(city);
    }

}

