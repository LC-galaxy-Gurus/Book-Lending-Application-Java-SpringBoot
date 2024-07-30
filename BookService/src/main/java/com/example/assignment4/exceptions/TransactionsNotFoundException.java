package com.example.assignment4.exceptions;

public class TransactionsNotFoundException extends RuntimeException {

	public TransactionsNotFoundException(String message) {
		super(message);
	}

}
