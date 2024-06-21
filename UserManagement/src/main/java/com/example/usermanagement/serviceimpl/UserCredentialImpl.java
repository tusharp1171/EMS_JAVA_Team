package com.example.usermanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermanagement.model.UserCredential;
import com.example.usermanagement.repository.UserCredentialRepository;
import com.example.usermanagement.service.UserCredentialService;

import jakarta.validation.Valid;
@Service
public class UserCredentialImpl implements UserCredentialService {

	@Autowired
	UserCredentialRepository userCredentialRepository;
	@Override
	public UserCredential createUserCredential(@Valid UserCredential userCredential) {
		
		return userCredentialRepository.save(userCredential);
	}
	@Override
	public List<UserCredential> getAllUserCredential() {
		// TODO Auto-generated method stub
		return userCredentialRepository.findAll() ;
	}

}
