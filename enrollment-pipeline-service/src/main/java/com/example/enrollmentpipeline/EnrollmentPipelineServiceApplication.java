package com.example.enrollmentpipeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
public class EnrollmentPipelineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrollmentPipelineServiceApplication.class, args);
	}
	  @Bean
	    RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
	 
}
