package com.example.communicationlog.service;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class EquiryMicroserviceApi {

	private String enquiryUrl = "http://192.168.1.:8083/";
}
