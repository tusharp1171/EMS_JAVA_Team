package com.example.communicationlog.service;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class SyallbusMicroserviceApi {

	private final String syallbusUrl = "http://192.168.1.123:8081";
}
