package com.example.assignment4.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


import com.example.assignment4.models.CustomerModel;
import com.example.assignment4.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private CustomerModel customer1;
    private CustomerModel customer2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer1 = CustomerModel.builder().fCustomerId(1).firstName("John Doe").emailId("john.doe@example.com").build();
        customer2 = CustomerModel.builder().fCustomerId(2).firstName("Jane Doe").emailId("jane.doe@example.com").build();
    }

    @Test
    void testGetAllCustomers() {
        // Given
        List<CustomerModel> customers = Arrays.asList(customer1, customer2);
        when(customerRepository.findAll()).thenReturn(customers);

        // When
        List<CustomerModel> result = customerService.getAllCustomers();

        // Then
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(customer1, customer2);
    }

    @Test
    void testGetCustomer() {
        // Given
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer1));

        // When
        CustomerModel result = customerService.getCustomer(1);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getFCustomerId()).isEqualTo(1);
        assertThat(result.getFirstName()).isEqualTo("John Doe");
    }

    @Test
    void testGetCustomerNotFound() {
        // Given
        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When
        CustomerModel result = customerService.getCustomer(999);

        // Then
        assertThat(result).isNull();
    }

    @Test
    void testSaveCustomer() {
        // Given
        when(customerRepository.save(any(CustomerModel.class))).thenReturn(customer1);

        // When
        CustomerModel result = customerService.saveCustomer(customer1);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getFCustomerId()).isEqualTo(1);
        assertThat(result.getFirstName()).isEqualTo("John Doe");
    }

    @Test
    void testDeleteCustomer() {
        // When
        customerService.deleteCustomer(1);

        // Then
        verify(customerRepository).deleteById(1);
    }
}

