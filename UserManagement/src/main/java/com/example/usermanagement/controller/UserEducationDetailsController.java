package com.example.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.usermanagement.model.UserEducationDetails;
import com.example.usermanagement.service.UserEducationDetailsService;

@RestController
@RequestMapping("/usereducationdetails")  
public class UserEducationDetailsController {
    
    @Autowired
    UserEducationDetailsService userEducationDetailsService;
    
    @PostMapping("/add")  
    public UserEducationDetails createUserEducationDetails(@RequestBody UserEducationDetails userEducation) {
        UserEducationDetails savedEDetail = userEducationDetailsService.createEducationDetails(userEducation);
        return savedEDetail; 
    }
    
    @GetMapping("/getall")  
    public List<UserEducationDetails> getAllUserEducationDetails() {
        return userEducationDetailsService.getAllUserEducationDetails();
    }
}
