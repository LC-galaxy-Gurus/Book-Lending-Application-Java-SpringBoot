package com.example.assignment4.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CustomerModel 
{
	//Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fCustomerId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String emailId;

    // Getters
    public int getFCustomerId()
    {
        return fCustomerId;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public String getAddress() 
    {
        return address;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getEmailId() 
    {
        return emailId;
    }

    // Setters
    public void setFCustomerId(int fCustomerId) 
    {
        this.fCustomerId = fCustomerId;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public void setEmailId(String emailId) 
    {
        this.emailId = emailId;
    }


}
