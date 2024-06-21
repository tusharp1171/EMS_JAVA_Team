package com.example.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.model.UserAdresses;
import com.example.usermanagement.service.UserAdressesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/useraddress")
public class UserAdressesController {
	@Autowired
	UserAdressesService userAdressesService;;
	
	@PostMapping
	public UserAdresses createUserAdresses(@Valid @RequestBody UserAdresses useradd) {
		return userAdressesService.createUserAdresses(useradd);
		
	}
	
	 @GetMapping("get")
    public ResponseEntity<List<UserAdresses>> getAllUserAdresses() {
        List<UserAdresses> users = userAdressesService.getAllAdresses();
        return ResponseEntity.ok(users);
	 }
}
