package com.example.usermanagement.service;

import java.util.List;

import com.example.usermanagement.model.UserAdresses;

import jakarta.validation.Valid;

public interface UserAdressesService {

	
	UserAdresses createUserAdresses(@Valid UserAdresses useradd);

	List<UserAdresses> getAllAdresses();

	
}
