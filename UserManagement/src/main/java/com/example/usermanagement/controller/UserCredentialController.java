package com.example.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.model.UserCredential;
import com.example.usermanagement.service.UserCredentialService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usercredentialdetails")
public class UserCredentialController {

	@Autowired
	UserCredentialService userCredentialService;
	
	@PostMapping("createcredential")
	public UserCredential createUserCredential(@Valid @RequestBody UserCredential userCredential) {
		return userCredentialService.createUserCredential(userCredential);
	}
	@GetMapping("getusercredential")

	public ResponseEntity<?> getAllUserCredential()
	{
		  List<UserCredential> adressesList =userCredentialService.getAllUserCredential();
		return new ResponseEntity<>(adressesList,HttpStatus.OK);
		
	}
}
