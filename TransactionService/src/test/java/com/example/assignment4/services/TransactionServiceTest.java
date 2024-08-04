package com.example.assignment4.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import com.example.assignment4.models.TransactionModel;
import com.example.assignment4.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTransactions() {
        TransactionModel transaction1 = TransactionModel.builder().transactionId(1).build();
        TransactionModel transaction2 = TransactionModel.builder().transactionId(2).build();
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        var transactions = transactionService.getAllTransactions();

        assertThat(transactions).hasSize(2);
        assertThat(transactions).containsExactly(transaction1, transaction2);
    }

    @Test
    void testGetTransaction() {
        TransactionModel transaction = TransactionModel.builder().transactionId(1).trxnType("Transaction1").build();
        when(transactionRepository.findById(anyInt())).thenReturn(Optional.of(transaction));
        var result = transactionService.getTransaction(1);
        assertThat(result).isNotNull();
        assertThat(result.getTransactionId()).isEqualTo(1);
        assertThat(result.getTrxnType()).isEqualTo("Transaction1");
    }

    @Test
    void testSaveTransaction() {
        TransactionModel transaction = TransactionModel.builder().transactionId(1).trxnType("Transaction1").build();
        when(transactionRepository.save(any(TransactionModel.class))).thenReturn(transaction);

        var result = transactionService.saveTransaction(transaction);

        assertThat(result).isNotNull();
        assertThat(result.getTransactionId()).isEqualTo(1);
        assertThat(result.getTrxnType()).isEqualTo("Transaction1");
    }

    @Test
    void testDeleteTransaction() {
        // We don't need to return anything for delete
        transactionService.deleteTransaction(1);

        // Verify that the delete method was called with the correct id
        verify(transactionRepository).deleteById(1);
    }
}
