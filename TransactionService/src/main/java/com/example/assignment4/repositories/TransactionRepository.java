package com.example.assignment4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.assignment4.models.TransactionModel;



@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Integer> {

}

