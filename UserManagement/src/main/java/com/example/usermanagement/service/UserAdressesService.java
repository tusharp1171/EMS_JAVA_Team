package com.example.usermanagement.service;

import java.util.List;

import com.example.usermanagement.model.UserAdresses;

import jakarta.validation.Valid;

public interface UserAdressesService {

	UserAdresses addUserAdresses(UserAdresses userAdresses);

	List<UserAdresses> getAlluserAdresses();


	
	void deleteUserAdressesByUserId(Long userId);

	

}
