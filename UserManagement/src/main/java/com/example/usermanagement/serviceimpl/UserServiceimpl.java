package com.example.usermanagement.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.usermanagement.dto.CouseEnquriymapDTO;
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
         
	    @Autowired
	    private RestTemplate restTemplate;
	    
	    @Override
	    @Transactional
	    public Users addUsers(@Valid Users users) {
	  
	        return userRepository.save(users);
	    }

		@Override
		public void addCouseEnquriymapDTO(CouseEnquriymapDTO couseEnquriymapDTO) {
			
		
		}
	    
	    
	    
	   
	}