package com.niit.customerservice.CustomerService.controller;

import com.niit.customerservice.CustomerService.domain.Customer;
import com.niit.customerservice.CustomerService.service.CustomerService;
import com.niit.customerservice.CustomerService.exception.CustomerAlreadyExists;
import com.niit.customerservice.CustomerService.exception.CustomerNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customerservice/")
public class CustomerController {

    private ResponseEntity responseEntity;
    private CustomerService customerService;

    @Autowired
    public CustomerController(final CustomerService customerService)
    {
        this.customerService=customerService;
    }

    @PostMapping("customer")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) throws CustomerAlreadyExists
    {
        try
        {
            customerService.saveCustomerDetail(customer);
            responseEntity=new ResponseEntity(customer, HttpStatus.CREATED);
        }
        catch(CustomerAlreadyExists e)
        {
            throw new CustomerAlreadyExists();
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<>("Error !!!Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR );
        }
        return responseEntity;
    }

    @DeleteMapping("customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") int customerId) throws CustomerNotFound
    {
        try
        {
            customerService.deleteCustomer(customerId);
            responseEntity=new ResponseEntity("Successfully deleted !!!", HttpStatus.OK);
        }
        catch(CustomerNotFound e)
        {
            throw e;
        }
        catch (Exception exception)
        {
            String ex=exception.getMessage();
            System.out.println(exception.getMessage());
            responseEntity=new ResponseEntity("Error !!! Try after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("customers")
    public ResponseEntity<?> getAllCustomer()
    {
        try
        {
            responseEntity=new ResponseEntity(customerService.getAllCustomerDetail(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity("Error !!! Try after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("customer1/{customerName}")
    public ResponseEntity<?> getCustomer(@PathVariable String customerName){
        System.out.println("Received customername:" +customerName);
        return  responseEntity=new ResponseEntity(customerService.getCustomerDetailByName(customerName),HttpStatus.OK);
    }

    @GetMapping("customers/{city}")
    public ResponseEntity<?> getAllCustomerByCity(@PathVariable String city){
        System.out.println("Received city:" +city);
        return responseEntity=new ResponseEntity(customerService.getAllCustomersByCity(city),HttpStatus.OK);
    }
}