package com.niit.customerservice.CustomerService.repository;

import com.niit.customerservice.CustomerService.domain.Address;
import com.niit.customerservice.CustomerService.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest

public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    private Address address;
    private Customer customer;

    @BeforeEach
    public void setUp()
    {
        address =new Address("DBlock","Surat","789546");
        customer=new Customer(2001,"kapil", 7896785788L,address);

    }

    @AfterEach
    public void tearDown()
    {
        address = null;
        customer = null;
    }

    @Test
    public void givenCustomerToSaveShouldReturnCustomer() {
        customerRepository.insert(customer);
        Customer customer1 = customerRepository.findById(customer.getCustomerId()).get();
        assertNotNull(customer1);
        assertEquals(customer.getCustomerId(), customer1.getCustomerId());
    }

    @Test
    public void givenCustomerToDeleteShouldDeleteCustomer() {
        customerRepository.insert(customer);
        Customer customer1 = customerRepository.findById(customer.getCustomerId()).get();
        customerRepository.delete(customer1);
        assertEquals(Optional.empty(),customerRepository.findById(customer.getCustomerId()));
    }

}

