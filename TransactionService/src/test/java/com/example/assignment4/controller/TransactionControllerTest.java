package com.example.assignment4.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import com.example.assignment4.controllers.TransactionController;
import com.example.assignment4.models.TransactionModel;
import com.example.assignment4.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTransactions() throws Exception {
        TransactionModel transaction1 = TransactionModel.builder().transactionId(1).trxnType("Transaction1").build();
        TransactionModel transaction2 = TransactionModel.builder().transactionId(2).trxnType("Transaction2").build();
        List<TransactionModel> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionService.getAllTransactions()).thenReturn(transactions);

        mockMvc.perform(get("/transactions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].transactionId", equalTo(1)))
                .andExpect(jsonPath("$[0].trxnType", equalTo("Transaction1")));
    }

    @Test
    void testGetTransaction() throws Exception {
        TransactionModel transaction = TransactionModel.builder().transactionId(1).trxnType("Transaction1").build();

        when(transactionService.getTransaction(anyInt())).thenReturn(transaction);

        mockMvc.perform(get("/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", equalTo(1)))
                .andExpect(jsonPath("$.trxnType", equalTo("Transaction1")));
    }

    @Test
    void testAddTransaction() throws Exception {
        TransactionModel transaction = TransactionModel.builder().transactionId(1).trxnType("Transaction1").build();

        when(transactionService.saveTransaction(any(TransactionModel.class))).thenReturn(transaction);

        mockMvc.perform(post("/transactions/addTransaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"transactionId\":1, \"description\":\"Transaction1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", equalTo(1)))
                .andExpect(jsonPath("$.trxnType", equalTo("Transaction1")));
    }

    @Test
    void testUpdateTransaction() throws Exception {
        TransactionModel transaction = TransactionModel.builder().transactionId(1).trxnType("UpdatedTransaction").build();
        when(transactionService.saveTransaction(any(TransactionModel.class))).thenReturn(transaction);

        mockMvc.perform(put("/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"transactionId\":1, \"description\":\"UpdatedTransaction\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", equalTo(1)))
                .andExpect(jsonPath("$.trxnType", equalTo("UpdatedTransaction")));
    }

    @Test
    void testDeleteTransaction() throws Exception {
        mockMvc.perform(delete("/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(transactionService).deleteTransaction(1);
    }
}
