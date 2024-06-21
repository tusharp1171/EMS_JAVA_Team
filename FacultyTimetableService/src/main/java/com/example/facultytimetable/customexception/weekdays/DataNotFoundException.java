package com.example.facultytimetable.customexception.weekdays;

public class DataNotFoundException extends RuntimeException{

	public DataNotFoundException() {
		super();
	}
	public DataNotFoundException(String msg) {
		super(msg);
	}
}
