package com.example.usermanagement.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.exception.ErrorDetails;
import com.example.usermanagement.model.UserType;
import com.example.usermanagement.service.UserTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/userType")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    @PostMapping("/add")
    public ResponseEntity<?> addUserType(@Valid @RequestBody UserType userType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            }
            ErrorDetails errorDetails = new ErrorDetails("Validation failed", LocalDateTime.now(), errors);
            return ResponseEntity.badRequest().body(errorDetails);
        }

        UserType savedUserType = userTypeService.addUserType(userType);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserType);
    }

}
