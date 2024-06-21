package com.example.ActivityManagement.ErrorDetails;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
	private int status;
    private String message;
    private LocalDateTime timestamp;
	    
}
