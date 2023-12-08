package com.example.assignment4.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.assignment4.models.TransactionModel;
import com.example.assignment4.repositories.TransactionRepository;


@Service
public class TransactionService 
{
    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionModel> getAllTransactions() 
    {
        return transactionRepository.findAll();
    }
    
    public TransactionModel getTransaction(int id) 
    {
        return transactionRepository.findById(id).orElse(null);
    }

    public TransactionModel saveTransaction(TransactionModel transaction) 
    {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(int id) 
    {
        transactionRepository.deleteById(id);
    }
}
