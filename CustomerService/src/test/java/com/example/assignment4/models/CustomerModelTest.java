package com.example.assignment4.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CustomerModelTest {

    private CustomerModel customer;

    @BeforeEach
    void setUp() {
        customer = CustomerModel.builder()
                .fCustomerId(1)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .phone("555-1234")
                .emailId("john.doe@example.com")
                .build();
    }

    @Test
    void testGettersAndSetters() {
        // Test getters
        assertThat(customer.getFCustomerId()).isEqualTo(1);
        assertThat(customer.getFirstName()).isEqualTo("John");
        assertThat(customer.getLastName()).isEqualTo("Doe");
        assertThat(customer.getAddress()).isEqualTo("123 Main St");
        assertThat(customer.getPhone()).isEqualTo("555-1234");
        assertThat(customer.getEmailId()).isEqualTo("john.doe@example.com");

        // Test setters
        customer.setFCustomerId(2);
        customer.setFirstName("Jane");
        customer.setLastName("Smith");
        customer.setAddress("456 Elm St");
        customer.setPhone("555-5678");
        customer.setEmailId("jane.smith@example.com");

        assertThat(customer.getFCustomerId()).isEqualTo(2);
        assertThat(customer.getFirstName()).isEqualTo("Jane");
        assertThat(customer.getLastName()).isEqualTo("Smith");
        assertThat(customer.getAddress()).isEqualTo("456 Elm St");
        assertThat(customer.getPhone()).isEqualTo("555-5678");
        assertThat(customer.getEmailId()).isEqualTo("jane.smith@example.com");
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        CustomerModel customer = new CustomerModel(1, "John", "Doe", "123 Main St", "555-1234", "john.doe@example.com");

        // Then
        assertThat(customer).isNotNull();
        assertThat(customer.getFCustomerId()).isEqualTo(1);
        assertThat(customer.getFirstName()).isEqualTo("John");
        assertThat(customer.getLastName()).isEqualTo("Doe");
        assertThat(customer.getAddress()).isEqualTo("123 Main St");
        assertThat(customer.getPhone()).isEqualTo("555-1234");
        assertThat(customer.getEmailId()).isEqualTo("john.doe@example.com");
    }

    @Test
    void testBuilder() {
        // Given
        CustomerModel customer = CustomerModel.builder()
                .fCustomerId(1)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .phone("555-1234")
                .emailId("john.doe@example.com")
                .build();

        // Then
        assertThat(customer).isNotNull();
        assertThat(customer.getFCustomerId()).isEqualTo(1);
        assertThat(customer.getFirstName()).isEqualTo("John");
        assertThat(customer.getLastName()).isEqualTo("Doe");
        assertThat(customer.getAddress()).isEqualTo("123 Main St");
        assertThat(customer.getPhone()).isEqualTo("555-1234");
        assertThat(customer.getEmailId()).isEqualTo("john.doe@example.com");
    }

    @Test
    void testNoArgsConstructor() {
        // Given
        CustomerModel customer = new CustomerModel();
        // Then
        assertThat(customer).isNotNull();
        assertThat(customer.getFCustomerId()).isEqualTo(0);
        assertThat(customer.getFirstName()).isNull();
        assertThat(customer.getLastName()).isNull();
        assertThat(customer.getAddress()).isNull();
        assertThat(customer.getPhone()).isNull();
        assertThat(customer.getEmailId()).isNull();
    }
}

