package com.example.usermanagement.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.exception.ErrorDetails;
import com.example.usermanagement.model.UserCredentials;
import com.example.usermanagement.service.UserCredentialsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("UserCredentials")
@CrossOrigin("*")
public class UserCredentialsController {

	@Autowired
	UserCredentialsService credentialsService;

//	  @PostMapping("/add/{uId}")
//	   public ResponseEntity<?> addUserCredentials(@Valid @RequestBody UserCredentials userCredentials, BindingResult bindingResult) {
//	        if (bindingResult.hasErrors()) {
//	            Map<String, String> errors = new HashMap<>();
//	            for (FieldError error : bindingResult.getFieldErrors()) {
//	                String fieldName = error.getField();
//	                String errorMessage = error.getDefaultMessage();
//	                errors.put(fieldName, errorMessage);
//	            }
//	           //   ErrorDetails errorDetails = new ErrorDetails(errors, LocalDateTime.now());
//	          //     return ResponseEntity.badRequest().body(errorDetails);
//	            }
//
////	            UserCredentials credentials = credentialsService.addUserCredentials(userCredentials);
////	            return ResponseEntity.status(HttpStatus.CREATED).body(credentials);
//	        }
	    }