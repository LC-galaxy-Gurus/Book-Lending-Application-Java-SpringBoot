package com.example.assignment4.exceptions;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.assignment4.exceptions.handlers.MailExceptionsHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class MailExceptionsHandlerTest {

    @InjectMocks
    private MailExceptionsHandler mailExceptionsHandler;

    @Mock
    private Logger logger;

    private MailException mailException;

    @Before
    public void setUp() {
        mailException = new MailException("Mail sending failed");
    }

    @Test
    public void testHandleMailException() throws Exception {
        Map<String, Object> expectedBody = new LinkedHashMap<>();
        expectedBody.put("timestamp", LocalDateTime.now());
        expectedBody.put("message", mailException.getMessage());

        ResponseEntity<Object> responseEntity = mailExceptionsHandler.handleMailException(mailException, null);

        assertEquals(HttpStatus.BAD_GATEWAY, responseEntity.getStatusCode());
        assertEquals(expectedBody, new ObjectMapper().readValue(responseEntity.getBody().toString(), Map.class));
    }
}
