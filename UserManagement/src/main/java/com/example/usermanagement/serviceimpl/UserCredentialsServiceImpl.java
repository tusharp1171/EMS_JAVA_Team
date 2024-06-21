package com.example.usermanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermanagement.model.UserCredentials;
import com.example.usermanagement.model.UserType;
import com.example.usermanagement.model.Users;
import com.example.usermanagement.repository.UserCredentialsRepo;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.repository.UserTypeRepo;
import com.example.usermanagement.service.UserCredentialsService;

import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {
	@Autowired
	UserCredentialsRepo credentialsRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserTypeRepo userTypeRepo;

	@Override
	public UserCredentials addUserCredentials(UserCredentials userCredentials) {
//	   	    UserType userType = userCredentials.getUsers().getUserType();
//	    if (userType.getId() == 0) { 
//	        userTypeRepo.save(userType);
//	    }
//	    Users users = new Users();
//	    users.setFirstName(userCredentials.getUsers().getFirstName());
//	    users.setLastName(userCredentials.getUsers().getLastName());
//	    users.setDob(userCredentials.getUsers().getDob());
//	    users.setUserType(userType);
//
//	    userRepository.save(users);
//
//	    userCredentials.setUsers(users);
	    return credentialsRepo.save(userCredentials);
	}

}
