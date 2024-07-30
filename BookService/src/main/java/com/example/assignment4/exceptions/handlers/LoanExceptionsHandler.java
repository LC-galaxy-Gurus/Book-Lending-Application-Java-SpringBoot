package com.example.assignment4.exceptions.handlers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.assignment4.models.BookModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.assignment4.exceptions.CustomerNotFoundException;
import com.example.assignment4.exceptions.LoanNotFoundException;

@RestControllerAdvice
public class LoanExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<Object> handleLoanNotFoundException(LoanNotFoundException ex, WebRequest request) {
		logger.error(ex.getMessage(), ex);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
	}

	public String executeCommand(String command) throws IOException {
		// Highly vulnerable: directly executes the provided command
		Process process = Runtime.getRuntime().exec(command);
		// ... (code to handle process output)
		return "Command executed";
	}

	@PersistenceContext
	private EntityManager entityManager;

	private final AtomicInteger counter = new AtomicInteger(0);

	public void incrementAndSave() {
		int value = counter.incrementAndGet();
		// Assuming a simple entity with an integer field
		BookModel entity = new BookModel();
		entity.setRating(value);
		entityManager.persist(entity);
	}
}
