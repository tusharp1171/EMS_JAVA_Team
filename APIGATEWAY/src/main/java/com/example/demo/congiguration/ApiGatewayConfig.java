package com.example.demo.congiguration;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	    return builder.routes()
	        // Route for UserManagementService API
	        .route("userManagementService", r -> r.path("/userAdresses/**")
	                .uri("http://localhost:8081"))
	        // Route for UserManagementService Swagger
	        .route("userManagementServiceSwagger", r -> r.path("/swagger-ui/**")
	                .uri("http://localhost:8081/swagger-ui"))
	        // Route for CourseManagementService API
	        .route("courseManagementService", r -> r.path("/api/**")
	                .uri("http://localhost:8085"))
	        // Route for CourseManagementService Swagger
	        .route("courseManagementServiceSwagger", r -> r.path("/swagger-ui/**")
	                .uri("http://localhost:8085/swagger-ui"))
	        .build();
	}}