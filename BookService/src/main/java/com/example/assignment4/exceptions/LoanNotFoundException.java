package com.example.assignment4.exceptions;

public class LoanNotFoundException extends RuntimeException{

	public LoanNotFoundException(String message){
		super(message);
	}
}
