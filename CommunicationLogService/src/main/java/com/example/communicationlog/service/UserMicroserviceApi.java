package com.example.communicationlog.service;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class UserMicroserviceApi {

	private final String userUrl = "http:192.168.1.103:8081";
}
