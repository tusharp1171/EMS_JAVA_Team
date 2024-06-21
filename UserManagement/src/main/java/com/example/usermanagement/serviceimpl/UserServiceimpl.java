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
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UserServiceimpl implements UserService {
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private UserTypeRepo userTypeRepo;

	    @Override
	    @Transactional
	    public Users addUsers(@Valid Users users) {
	   

//	        // Ensure bidirectional relationships are maintained
//	        if (users.getUserAdresses() != null) {
//	            users.getUserAdresses().forEach(address -> address.setUsers(users));
//	        }
//	        if (users.getUserCredentials() != null) {
//	            users.getUserCredentials().forEach(credential -> credential.setUsers(users));
//	        }
//	        if (users.getUserEducationDetails() != null) {
//	            users.getUserEducationDetails().forEach(educationDetail -> educationDetail.setUsers(users));
//	        }

	        // Save the Users entity and its related entities
	        return userRepository.save(users);
	    }
	}