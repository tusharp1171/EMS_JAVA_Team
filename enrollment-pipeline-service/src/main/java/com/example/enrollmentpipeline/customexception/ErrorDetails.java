package com.example.enrollmentpipeline.customexception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorDetails {
	private String message;
    private LocalDateTime timestamp;
    private Map<String, String> fieldErrors;

    // Constructor for validation errors
    public ErrorDetails(String message, LocalDateTime timestamp, Map<String, String> fieldErrors) {
        this.message = message;
        this.timestamp = timestamp;
        this.fieldErrors = fieldErrors;
    }

    public ErrorDetails(Map<String, String> errors, LocalDateTime now) {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters (necessary for Jackson serialization)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}