package com.example.assignment4.exceptions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import com.example.assignment4.exceptions.CustomerAlreadyRegisteredException;
import com.example.assignment4.exceptions.handlers.CustomerExceptionsHandler;
import com.example.assignment4.exceptions.CustomerNotFoundException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RunWith(MockitoJUnitRunner.class)
public class CustomerExceptionsHandlerTest {

    @InjectMocks
    private CustomerExceptionsHandler customerExceptionsHandler;

    @Mock
    private WebRequest request;

    private Map<String, Object> expectedBody;

    @Before
    public void setUp() {
        expectedBody = new HashMap<>();
        expectedBody.put("timestamp", LocalDateTime.now());
    }

    @Test
    public void testHandleCustomerNotFoundException() {
        String expectedMessage = "Customer not found";
        CustomerNotFoundException ex = new CustomerNotFoundException(expectedMessage);

        when(request.getDescription(Boolean.FALSE)).thenReturn(expectedMessage);

        ResponseEntity<Object> responseEntity = customerExceptionsHandler.handleCustomerNotFoundException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        //assertEquals(expectedBody, responseEntity.getBody());
        assertEquals(expectedMessage, ((Map<String, Object>) responseEntity.getBody()).get("message"));
    }

    @Test
    public void testHandleCustomerAlreadyRegisteredException() {
        String expectedMessage = "Customer already registered";
        CustomerAlreadyRegisteredException ex = new CustomerAlreadyRegisteredException(expectedMessage);

        when(request.getDescription(Boolean.FALSE)).thenReturn(expectedMessage);

        ResponseEntity<Object> responseEntity = customerExceptionsHandler.handleCustomerAlreadyRegisteredException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        //assertEquals(expectedBody, responseEntity.getBody());
        assertEquals(expectedMessage, ((Map<String, Object>) responseEntity.getBody()).get("message"));
    }
}
