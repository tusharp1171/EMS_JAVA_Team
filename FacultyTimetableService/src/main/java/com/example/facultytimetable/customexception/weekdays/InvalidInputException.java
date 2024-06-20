package com.example.facultytimetable.customexception.weekdays;

public class InvalidInputException extends RuntimeException {
	
	public InvalidInputException() {
		super();
	}
	public InvalidInputException(String msg) {
		super(msg);
	}
	

}
