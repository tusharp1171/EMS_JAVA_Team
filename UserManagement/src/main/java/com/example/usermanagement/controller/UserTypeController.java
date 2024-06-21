package com.example.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.model.UserType;
import com.example.usermanagement.service.UserTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    @PostMapping("/addusertype")
    public ResponseEntity<?> addUserType(@Valid @RequestBody UserType userType, BindingResult bindingResult) {
        
        UserType savedUserType = userTypeService.addUserType(userType);
        return ResponseEntity.status(HttpStatus.CREATED).body("User type added successfully");
    }

    @GetMapping("/getallusertypes") // Adjusted mapping
    public List<UserType> getAllUserTypes() {
        List<UserType> userTypes = userTypeService.getAllUserTypes();
        return userTypes;
    }
    

    @GetMapping("/gettypeuser/{id}")
    public ResponseEntity<UserType> getUserTypeById(@PathVariable Long id) {
        UserType userType = userTypeService.getUserTypeById(id);
        if (userType != null) {
            return ResponseEntity.ok(userType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    

}
