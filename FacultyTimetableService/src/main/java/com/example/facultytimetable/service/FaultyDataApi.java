package com.example.facultytimetable.service;

import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class FaultyDataApi {

	private String faultyIdUrl = "http://192.168.1.135:8080/api/test/tokenusername";
}
