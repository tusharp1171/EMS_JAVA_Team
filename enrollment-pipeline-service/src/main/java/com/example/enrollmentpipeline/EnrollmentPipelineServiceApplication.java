package com.example.enrollmentpipeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class EnrollmentPipelineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrollmentPipelineServiceApplication.class, args);
	}
	  @Bean
	    RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
}

