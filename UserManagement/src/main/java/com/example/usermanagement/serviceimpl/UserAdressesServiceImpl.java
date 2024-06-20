package com.example.usermanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermanagement.model.UserAdresses;
import com.example.usermanagement.repository.UserAdressesRepository;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserAdressesService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


@Service
public class UserAdressesServiceImpl implements UserAdressesService{
	
	@Autowired
	UserAdressesRepository adressesRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserAdresses addUserAdresses( UserAdresses userAdresses ,int id) {
		userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("UserAdresses not found for ID: " + id));
		
		return adressesRepository.save(userAdresses) ;
	}

	@Override
	public List<UserAdresses> getAlluserAdresses() {
		return adressesRepository.findAll();
		
	}

}
