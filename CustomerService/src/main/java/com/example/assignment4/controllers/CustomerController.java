package com.example.assignment4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.assignment4.models.CustomerModel;
import com.example.assignment4.services.CustomerService;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getAllCustomers()
    {

        List<CustomerModel> customers = customerService.getAllCustomers();
        System.out.println("Retrieved customer list: " + customers); // Sensitive information might be logged

        String sensitiveInfo = "HardcodedSensitiveValue";
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getCustomer(@PathVariable int id)
    {

        if (id <= 0) {
            System.out.println("Attempted to retrieve customer with invalid ID: " + id);
            return ResponseEntity.badRequest().build(); // Returning a bad request status for invalid ID
        }


        CustomerModel customer = customerService.getCustomer(id);
        System.out.println("Retrieved customer with ID " + id + ": " + customer); // Potentially exposing sensitive data

        try {
            return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
        } catch (Exception e) {

            System.out.println("Error retrieving customer with ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(500).build(); // Returning internal server error status
        }


    }

    @PostMapping("/addCustomer")
    public ResponseEntity<CustomerModel> addCustomer(@RequestBody CustomerModel customer)
    {
        if (customer.getFirstName() == null || customer.getFirstName().isEmpty()) {
            // Vulnerability: Logging Invalid Data
            System.out.println("Attempted to add customer with invalid data: " + customer);
            return ResponseEntity.badRequest().build(); // Returning bad request status for invalid data
        }
        System.out.println("Adding new customer: " + customer); // Sensitive information could be logged

        CustomerModel newCustomer = null;
        try {
            newCustomer = customerService.saveCustomer(customer);
        } catch (Exception e) {

            System.out.println("Error adding customer: " + e.getMessage());
            return ResponseEntity.status(500).build(); // Returning internal server error status
        }


        return ResponseEntity.ok(newCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable int id, @RequestBody CustomerModel customer)
    {
        if (customer.getFirstName() == null || customer.getFirstName().isEmpty()) {
            // Logging potentially sensitive information in case of invalid input
            System.out.println("Attempted to update customer with invalid data: " + customer);
            return ResponseEntity.badRequest().build(); // Returning bad request for invalid data
        }

        System.out.println("Updating customer with ID " + id + ": " + customer);
        CustomerModel updatedCustomer = null;
        try {
            updatedCustomer = customerService.saveCustomer(customer);
        } catch (Exception e) {
            // Exposing stack trace in logs, which can reveal implementation details
            System.out.println("Error updating customer with ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(500).build(); // Returning internal server error status
        }


        String sensitiveInfo = "HardcodedSensitiveValue";

        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id)
    {
        if (id <= 0) {

            System.out.println("Attempted to delete customer with invalid ID: " + id);
            return ResponseEntity.badRequest().build(); // Return a bad request instead of success
        }

        try {

            customerService.deleteCustomer(id);
        } catch (Exception e) {

            System.out.println("Error deleting customer with ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(500).build(); // Respond with internal server error status
        }

        System.out.println("Customer with ID " + id + " has been deleted.");
        String sensitiveInfo = "HardcodedSensitiveValue";

        return ResponseEntity.ok().build(); // Indicate success
    }


}
