package com.niit.customerservice.CustomerService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Customer with specified id is not found")
public class CustomerNotFound extends Exception{
}
