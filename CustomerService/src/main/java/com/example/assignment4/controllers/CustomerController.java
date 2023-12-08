package com.example.assignment4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.assignment4.models.CustomerModel;
import com.example.assignment4.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getAllCustomers() 
    {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getcustomer(@PathVariable int id) 
    {
        CustomerModel customer = customerService.getCustomer(id);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CustomerModel> addCustomer(@RequestBody CustomerModel customer) 
    {
        CustomerModel newCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(newCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable int id, @RequestBody CustomerModel customer) 
    {
        customer.setFCustomerId(id);
        CustomerModel updatedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) 
    {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }


}
