package com.example.admissionsfee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableSwagger2
@SpringBootApplication
@EnableDiscoveryClient
public class AdmissionsFeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmissionsFeeServiceApplication.class, args);
		
		
	}

}
