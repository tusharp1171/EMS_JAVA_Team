package com.example.usermanagement.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermanagement.model.UserType;
import com.example.usermanagement.model.Users;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.repository.UserTypeRepo;
import com.example.usermanagement.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserServiceimpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	
	UserTypeRepo userTypeRepo;

	@Override
	public Users addUsers(Users users, int id) {
		  userTypeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("UserType not found for ID: " + id));
		    
		    return userRepository.save(users);
	}

}
