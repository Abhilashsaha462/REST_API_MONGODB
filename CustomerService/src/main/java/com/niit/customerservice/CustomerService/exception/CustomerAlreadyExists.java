package com.niit.customerservice.CustomerService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "This customer is already registered.")
public class CustomerAlreadyExists extends Exception{
}
