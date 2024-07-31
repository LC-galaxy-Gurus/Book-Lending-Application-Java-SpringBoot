package com.example.assignment4.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.assignment4.models.TransactionModel;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TransactionModelTest {

    @Test
    void testBuilder() {
        // Given
        Date now = new Date();
        TransactionModel transaction = TransactionModel.builder()
                .transactionId(1)
                .customerId(123)
                .bookId(456)
                .trxnDate(now)
                .trxnType("Purchase")
                .build();

        // Then
        assertNotNull(transaction);
        assertEquals(1, transaction.getTransactionId());
        assertEquals(123, transaction.getCustomerId());
        assertEquals(456, transaction.getBookId());
        assertEquals(now, transaction.getTrxnDate());
        assertEquals("Purchase", transaction.getTrxnType());
    }

    @Test
    void testGettersAndSetters() {
        // Given
        TransactionModel transaction = new TransactionModel();
        Date now = new Date();

        // When
        transaction.setTransactionId(1);
        transaction.setCustomerId(123);
        transaction.setBookId(456);
        transaction.setTrxnDate(now);
        transaction.setTrxnType("Purchase");

        // Then
        assertEquals(1, transaction.getTransactionId());
        assertEquals(123, transaction.getCustomerId());
        assertEquals(456, transaction.getBookId());
        assertEquals(now, transaction.getTrxnDate());
        assertEquals("Purchase", transaction.getTrxnType());
    }

    @Test
    void testNoArgsConstructor() {
        // Given
        TransactionModel transaction = new TransactionModel();

        // Then
        assertNotNull(transaction);
        assertEquals(0, transaction.getTransactionId());
        assertEquals(0, transaction.getCustomerId());
        assertEquals(0, transaction.getBookId());
        assertEquals(null, transaction.getTrxnDate());
        assertEquals(null, transaction.getTrxnType());
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        Date now = new Date();
        TransactionModel transaction = new TransactionModel(1, 123, 456, now, "Purchase");

        // Then
        assertNotNull(transaction);
        assertEquals(1, transaction.getTransactionId());
        assertEquals(123, transaction.getCustomerId());
        assertEquals(456, transaction.getBookId());
        assertEquals(now, transaction.getTrxnDate());
        assertEquals("Purchase", transaction.getTrxnType());
    }
}

