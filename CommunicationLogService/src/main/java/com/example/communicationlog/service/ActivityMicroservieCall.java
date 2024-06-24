package com.example.communicationlog.service;

import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class ActivityMicroservieCall {
	
	private String actUrl = "http://192.168.1.116/api/activities"; 

}
