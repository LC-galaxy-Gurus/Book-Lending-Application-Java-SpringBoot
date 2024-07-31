package com.example.assignment4.models;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionModel 
{
	//Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private int customerId;
    private int bookId;
    private Date trxnDate;
    private String trxnType;

    // Getters
    public int getTransactionId() 
    {
        return transactionId;
    }

    public int getCustomerId() 
    {
        return customerId;
    }

    public int getBookId() 
    {
        return bookId;
    }

    public Date getTrxnDate() 
    {
        return trxnDate;
    }

    public String getTrxnType() 
    {
        return trxnType;
    }

    // Setters
    public void setTransactionId(int transactionId) 
    {
        this.transactionId = transactionId;
    }

    public void setCustomerId(int customerId) 
    {
        this.customerId = customerId;
    }

    public void setBookId(int bookId) 
    {
        this.bookId = bookId;
    }

    public void setTrxnDate(Date trxnDate) 
    {
        this.trxnDate = trxnDate;
    }

    public void setTrxnType(String trxnType) 
    {
        this.trxnType = trxnType;
    }

}
