package com.example.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.model.Users;
import com.example.usermanagement.service.UserService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("Users")
public class UserController {

	@Autowired
	
	UserService userService;
	
	@PostMapping("/add")
    public ResponseEntity<Users> addUsers(@Valid @RequestBody Users users, @PathVariable int id) {
		Users users2=userService.addUsers(users,id);
		return new  ResponseEntity<Users> (users2,HttpStatus.CREATED) ;
		
	}
	
	
}
