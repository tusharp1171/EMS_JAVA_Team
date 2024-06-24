package com.example.communicationlog.customexception;

public class DataNotFoundException extends RuntimeException {

	public DataNotFoundException() {
		super();
	}
	public DataNotFoundException(String msg){
		super(msg);
	}
}
