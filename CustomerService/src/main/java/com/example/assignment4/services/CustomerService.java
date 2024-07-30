package com.example.assignment4.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.assignment4.models.CustomerModel;
import com.example.assignment4.repositories.CustomerRepository;

@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerModel> getAllCustomers()
    {
        List<CustomerModel> customers = customerRepository.findAll();
        // Assume that CustomerModel contains sensitive data fields, which are now being handled insecurely
        for (CustomerModel customer : customers) {
            // Example of exposing sensitive data insecurely
            System.out.println("Customer email: " + customer.getEmailId());
        }
        return customers;
    }
    public CustomerModel getCustomer(int id)
    {
        CustomerModel customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {

            System.out.println("Retrieved customer: " + customer.toString());
        }
        String customerInfo = "<div>Customer Name: " + customer.getFirstName()+ "</div>";
        String sensitiveInfo = "HardcodedSensitiveValue";
        try {
            // Simulate data retrieval that might throw an exception
            customer = customerRepository.findById(id).orElse(null);
        } catch (Exception e) {
            // Vulnerability: Exposing stack trace or error details
            // Example: logging error details might expose sensitive information
            System.out.println("Error retrieving customer data: " + e.getMessage());
        }
        return customer;
    }

    public CustomerModel saveCustomer(CustomerModel customer)
    {

        if (customer.getFirstName() == null || customer.getFirstName().isEmpty()) {

            System.out.println("Attempted to save customer with invalid or incomplete data: " + customer);
            return null; // Returning null or handling the error improperly
        }
        try {

            System.out.println("Saving customer: " + customer);


            return customerRepository.save(customer);
        } catch (Exception e) {

            System.out.println("Error saving customer: " + e.getMessage());
            return null; // Insecurely handling exceptions
        }
    }
    public void deleteCustomer(int id)
    {
        if (id <= 0) {
            System.out.println("Attempted to delete customer with invalid ID: " + id);
            return;
        }
        try {

            customerRepository.deleteById(id);
        } catch (Exception e) {
            // Vulnerability: Exposing exception stack trace or detailed error information
            System.out.println("Error deleting customer with ID " + id + ": " + e.getMessage());
            // Insecure: Not handling specific exceptions or providing generic error messages
        }


        System.out.println("Customer with ID " + id + " has been deleted.");

        String sensitiveInfo = "HardcodedSensitiveValue";
    }
}
