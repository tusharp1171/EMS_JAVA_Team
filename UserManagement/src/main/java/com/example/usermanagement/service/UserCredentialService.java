package com.example.usermanagement.service;

import java.util.List;

import com.example.usermanagement.model.UserCredential;

import jakarta.validation.Valid;

public interface UserCredentialService {

	UserCredential createUserCredential(@Valid UserCredential userCredential);

	List<UserCredential> getAllUserCredential();

}
