package com.niit.customerservice.CustomerService.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@AllArgsConstructor
@Getter
@Setter
@Document
@NoArgsConstructor
public class Customer {
   @Id
   private int customerId;
   private String customerName;
   private long customerPhoneNo;
   private Address address;

}
