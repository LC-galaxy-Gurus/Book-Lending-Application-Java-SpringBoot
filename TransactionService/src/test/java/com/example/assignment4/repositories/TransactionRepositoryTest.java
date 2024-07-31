package com.example.assignment4.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.assignment4.models.TransactionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.Optional;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    private TransactionModel transaction;

    @BeforeEach
    void setUp() {
        transaction = new TransactionModel();
        transaction.setCustomerId(123);
        transaction.setBookId(456);
        transaction.setTrxnDate(new Date());
        transaction.setTrxnType("Purchase");

        // Persist the transaction entity
        testEntityManager.persistAndFlush(transaction);
    }

    @Test
    void testFindById() {
        Optional<TransactionModel> foundTransaction = transactionRepository.findById(transaction.getTransactionId());

        assertThat(foundTransaction).isPresent();
        assertThat(foundTransaction.get().getCustomerId()).isEqualTo(transaction.getCustomerId());
        assertThat(foundTransaction.get().getBookId()).isEqualTo(transaction.getBookId());
        assertThat(foundTransaction.get().getTrxnDate()).isEqualTo(transaction.getTrxnDate());
        assertThat(foundTransaction.get().getTrxnType()).isEqualTo(transaction.getTrxnType());
    }

    @Test
    void testSave() {
        TransactionModel newTransaction = new TransactionModel();
        newTransaction.setCustomerId(789);
        newTransaction.setBookId(101);
        newTransaction.setTrxnDate(new Date());
        newTransaction.setTrxnType("Return");

        TransactionModel savedTransaction = transactionRepository.save(newTransaction);

        assertThat(savedTransaction).isNotNull();
        assertThat(savedTransaction.getTransactionId()).isGreaterThan(0);
        assertThat(savedTransaction.getCustomerId()).isEqualTo(newTransaction.getCustomerId());
        assertThat(savedTransaction.getBookId()).isEqualTo(newTransaction.getBookId());
        assertThat(savedTransaction.getTrxnDate()).isEqualTo(newTransaction.getTrxnDate());
        assertThat(savedTransaction.getTrxnType()).isEqualTo(newTransaction.getTrxnType());
    }

    @Test
    void testDelete() {
        TransactionModel foundTransaction = transactionRepository.findById(transaction.getTransactionId()).orElse(null);
        assertThat(foundTransaction).isNotNull();

        transactionRepository.delete(foundTransaction);

        Optional<TransactionModel> deletedTransaction = transactionRepository.findById(transaction.getTransactionId());
        assertThat(deletedTransaction).isNotPresent();
    }
}

