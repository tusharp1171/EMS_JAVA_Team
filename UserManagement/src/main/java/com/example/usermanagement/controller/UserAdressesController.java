package com.example.usermanagement.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.exception.ErrorDetails;
import com.example.usermanagement.model.UserAdresses;
import com.example.usermanagement.service.UserAdressesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("userAdresses")
public class UserAdressesController {

	@Autowired
	UserAdressesService adressesService;
	
	@PostMapping("/add/{uid}")
	public ResponseEntity<?> addUserAdresses(@Valid @RequestBody UserAdresses userAdresses ,@PathVariable("uid") int id, BindingResult bindingResult) {
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

        UserAdresses adresses = adressesService.addUserAdresses(userAdresses,id);
            return ResponseEntity.status(HttpStatus.CREATED).body(adresses);
        }
	
	@GetMapping("/findAlladress")
	public ResponseEntity<?> getAlluserAdresses()
	{
		  List<UserAdresses> adressesList =adressesService.getAlluserAdresses();
		return new ResponseEntity<>(adressesList,HttpStatus.OK);
		
	}
    }