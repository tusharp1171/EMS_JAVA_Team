package com.example.ActivityManagement.CustomException;

public class InvalidRequestException extends RuntimeException {

	public InvalidRequestException(String message) {
		super(message);
	}
	
	public InvalidRequestException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
