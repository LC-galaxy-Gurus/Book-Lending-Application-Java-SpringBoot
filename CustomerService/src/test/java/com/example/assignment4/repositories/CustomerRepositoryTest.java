package com.example.assignment4.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.assignment4.models.CustomerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        // Add test data to the in-memory database
        CustomerModel customer = new CustomerModel(1, "John", "Doe", "123 Main St", "555-1234", "john.doe@example.com");
        customerRepository.save(customer);
    }

    @Test
    void testFindById() {
        // When
        Optional<CustomerModel> result = customerRepository.findById(1);
        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getFirstName()).isEqualTo("John");
        assertThat(result.get().getLastName()).isEqualTo("Doe");
    }

    @Test
    void testSaveCustomer() {
        // Given
        CustomerModel newCustomer = new CustomerModel(2, "Jane", "Smith", "456 Elm St", "555-5678", "jane.smith@example.com");
        // When
        CustomerModel savedCustomer = customerRepository.save(newCustomer);
        // Then
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getFCustomerId()).isEqualTo(2);
        assertThat(savedCustomer.getFirstName()).isEqualTo("Jane");
        assertThat(savedCustomer.getLastName()).isEqualTo("Smith");
    }

    @Test
    void testDeleteCustomer() {
        // Given
        customerRepository.deleteById(1);
        // When
        Optional<CustomerModel> result = customerRepository.findById(1);
        // Then
        assertThat(result).isNotPresent();
    }
}

