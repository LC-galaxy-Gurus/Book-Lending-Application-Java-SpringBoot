package com.example.assignment4.exceptions;

public class TransactionFailedException extends RuntimeException {

	public TransactionFailedException(String message) {
		super(message);
	}
	
}
