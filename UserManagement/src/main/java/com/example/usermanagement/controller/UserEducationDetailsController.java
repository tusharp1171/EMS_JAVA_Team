package com.example.usermanagement.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.exception.ErrorDetails;
import com.example.usermanagement.model.UserEducationDetails;
import com.example.usermanagement.service.UserEducationDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("userEducationDetails")
public class UserEducationDetailsController {
@Autowired
UserEducationDetailsService educationDetailsService;

@PostMapping("/add")
public ResponseEntity<?> addEducationDetailsService(@Valid @RequestBody UserEducationDetails userEducationDetails, BindingResult bindingResult) {
     if (bindingResult.hasErrors()) {
         Map<String, String> errors = new HashMap<>();
         for (FieldError error : bindingResult.getFieldErrors()) {
             String fieldName = error.getField();
             String errorMessage = error.getDefaultMessage();
             errors.put(fieldName, errorMessage);
         }
           ErrorDetails errorDetails = new ErrorDetails(errors, LocalDateTime.now());
            return ResponseEntity.badRequest().body(errorDetails);
         }

     UserEducationDetails  educationDetails= educationDetailsService.addEducationDetailsService(userEducationDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(educationDetails);
    }

@DeleteMapping("/usereducation/{userId}")
public String deleteUserEducationDetailsByUserId(@PathVariable Long userId) {
	educationDetailsService.deleteUserEducationDetailsByUserId(userId);
	return "deleted";
}
}
