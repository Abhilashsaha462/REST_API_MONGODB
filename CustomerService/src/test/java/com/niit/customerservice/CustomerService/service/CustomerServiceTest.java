package com.niit.customerservice.CustomerService.service;

import com.niit.customerservice.CustomerService.domain.Address;
import com.niit.customerservice.CustomerService.domain.Customer;
import com.niit.customerservice.CustomerService.repository.CustomerRepository;
import com.niit.customerservice.CustomerService.exception.CustomerAlreadyExists;
import com.niit.customerservice.CustomerService.exception.CustomerNotFound;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {


    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer1,customer2;
    List<Customer> customerList;
    Address address1,address2;

    @BeforeEach
    public void setUp(){
        address1=new Address("Apartment","Siliguri","760006");
        customer1=new Customer(1001,"Ahilash",8373861031L,address1);
        address2=new Address("BlockApartment","Mumbai","768080");
        customer2=new Customer(1002,"Santa",7318656296L,address2);
        customerList= Arrays.asList(customer1,customer2);
    }
    @AfterEach
    public void tearDown(){
        customer1=null;
        customer2=null;
    }
    @Test
    public void givenCustomerToSavedReturnSavedCustomerSuccess() throws CustomerAlreadyExists{

        when(customerRepository.findById(customer1.getCustomerId())).thenReturn(Optional.ofNullable(null));
        when(customerRepository.save(any())).thenReturn(customer1);
        assertEquals(customer1,customerService.saveCustomerDetail(customer1));
        verify(customerRepository,times(1)).save(any());
        verify(customerRepository,times(1)).findById(any());
    }

    @Test
    public void givenCustomerToDeleteShouldDeleteCustomer() throws CustomerNotFound {
        when(customerRepository.findById(customer1.getCustomerId())).thenReturn(Optional.of(customer1));
//        assertEquals(customer1,customerService.deleteCustomer(customer1));
        assertTrue(customerService.deleteCustomer(customer1.getCustomerId()));
        verify(customerRepository,times(1)).findById(any());
        verify(customerRepository,times(1)).deleteById(any());
    }

}