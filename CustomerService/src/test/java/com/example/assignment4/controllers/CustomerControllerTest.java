package com.example.assignment4.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;

import com.example.assignment4.models.CustomerModel;
import com.example.assignment4.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private CustomerModel customer1;
    private CustomerModel customer2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer1 = CustomerModel.builder().fCustomerId(1).firstName("John Doe")
                .lastName("john.doe@example.com").build();
        customer2 = CustomerModel.builder().fCustomerId(2).firstName("Jane Doe")
                .lastName("john.doe@example.com").build();
    }

    @Test
    void testGetAllCustomers() throws Exception {
        List<CustomerModel> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].fcustomerId", equalTo(1)))
                .andExpect(jsonPath("$[0].firstName", equalTo("John Doe")))
                .andExpect(jsonPath("$[1].fcustomerId", equalTo(2)))
                .andExpect(jsonPath("$[1].firstName", equalTo("Jane Doe")));
    }

    @Test
    void testGetCustomer() throws Exception {
        when(customerService.getCustomer(anyInt())).thenReturn(customer1);

        mockMvc.perform(get("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fcustomerId", equalTo(1)))
                .andExpect(jsonPath("$.firstName", equalTo("John Doe")));
    }

    @Test
    void testGetCustomerNotFound() throws Exception {
        when(customerService.getCustomer(anyInt())).thenReturn(null);

        mockMvc.perform(get("/customers/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddCustomer() throws Exception {
        when(customerService.saveCustomer(any(CustomerModel.class))).thenReturn(customer1);

        mockMvc.perform(post("/customers/addCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fcustomerId\":1, \"firstName\":\"John Doe\", \"emailId\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fcustomerId", equalTo(1)))
                .andExpect(jsonPath("$.firstName", equalTo("John Doe")));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        when(customerService.saveCustomer(any(CustomerModel.class))).thenReturn(customer1);

        mockMvc.perform(put("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fcustomerId\":1, \"firstName\":\"John Doe\", \"emailId\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fcustomerId", equalTo(1)))
                .andExpect(jsonPath("$.firstName", equalTo("John Doe")));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomer(1);
    }
}

