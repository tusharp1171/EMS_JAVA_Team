package com.example.usermanagement.service;

import com.example.usermanagement.dto.CouseEnquriymapDTO;
import com.example.usermanagement.model.Users;

import jakarta.validation.Valid;

public interface UserService {

	Users addUsers(@Valid Users users);

	void addCouseEnquriymapDTO(CouseEnquriymapDTO couseEnquriymapDTO);

	

}
