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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.exception.ErrorDetails;
import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.model.UserAdresses;
import com.example.usermanagement.service.UserAdressesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("userAdresses")
public class UserAdressesController {

	@Autowired
	UserAdressesService adressesService;

	@PostMapping("/add")
	public ResponseEntity<?> addUserAdresses(@Valid @RequestBody UserAdresses userAdresseslist) {
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            for (FieldError error : bindingResult.getFieldErrors()) {
//                String fieldName = error.getField();
//                String errorMessage = error.getDefaultMessage();
//                errors.put(fieldName, errorMessage);
//            }
//              ErrorDetails errorDetails = new ErrorDetails(errors, LocalDateTime.now());
//              return ResponseEntity.badRequest().body(errorDetails);
//            }

	UserAdresses adresses = adressesService.addUserAdresses(userAdresseslist);
		return ResponseEntity.status(HttpStatus.CREATED).body(adresses);
	}

	@GetMapping("/findAlladress")
	public ResponseEntity<?> getAlluserAdresses() {
		List<UserAdresses> adressesList = adressesService.getAlluserAdresses();
		return new ResponseEntity<>(adressesList, HttpStatus.OK);

	}

	@DeleteMapping("/user/{userId}")
	public String deleteUserAdressesByUserId(@PathVariable Long userId) {
		adressesService.deleteUserAdressesByUserId(userId);
		return "deleted";
	}
	
	@GetMapping("/Find/{userId}")
	public ResponseEntity<?> getUserAdressesByUserId(@PathVariable Long userId) {
		UserAdresses userAdressesUserId=adressesService.getUserAdressesByUserId(userId);
		return  new ResponseEntity<>(userAdressesUserId, HttpStatus.OK);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> updateAddressess(@RequestBody UserAdresses userAdresses, @PathVariable Long userId) {
	    try {
	        UserAdresses updatedAddresses = adressesService.updateAdresses(userAdresses, userId);
	        return new ResponseEntity<>(updatedAddresses, HttpStatus.OK);
	    } catch (UserNotFoundException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}
}