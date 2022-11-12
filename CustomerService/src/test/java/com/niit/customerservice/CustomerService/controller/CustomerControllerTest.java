package com.niit.customerservice.CustomerService.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.customerservice.CustomerService.domain.Address;
import com.niit.customerservice.CustomerService.domain.Customer;
import com.niit.customerservice.CustomerService.service.CustomerService;
import com.niit.customerservice.CustomerService.exception.CustomerNotFound;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    private Customer customer1, customer2;
    private Address address1, address2;
    List<Customer> customerList;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setUp() {
        address1 = new Address("line1","state1","pincode1");
        customer1 = new Customer(1001,"Johny",8373861031L,address1);
        address2 = new Address("line2","state2","pincode2");
        customer2 = new Customer(1002,"Abhi",7318656296L,address2);
        customerList = Arrays.asList(customer1, customer2);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @AfterEach
    public void tearDown() {
        customer1 = null;
        customer2 = null;
    }

    @Test
    public void givenCustomerTl065toSaveReturnSaveProductSuccess() throws Exception {
        when(customerService.saveCustomerDetail(any())).thenReturn(customer1);
        mockMvc.perform(post("/api/v1/customerservice/customer")
               .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(customer1)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(customerService,times(1)).saveCustomerDetail(any());

    }

    @Test
    public void deleteCustomerToReturnDeleteProductSuccess() throws CustomerNotFound, Exception {
        when(customerService.deleteCustomer(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/api/v1/customerservice/customer/1031")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(customerService,times(1)).deleteCustomer(anyInt());
    }

  private static String jsonToString(final Object ob) throws JsonProcessingException
  {
        String result;
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            System.out.println("Json Content that has been posted:\n"+jsonContent);
            result = jsonContent;
        }
        catch (JsonProcessingException e)
        {
            result = "JSON processing error";
        }
        return result;
  }
}
