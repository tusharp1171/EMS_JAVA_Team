package com.example.usermanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermanagement.model.UserAdresses;
import com.example.usermanagement.repository.UserAdressesRepository;
import com.example.usermanagement.service.UserAdressesService;

import jakarta.validation.Valid;
@Service
public class UserAdressesServiceImpl implements UserAdressesService {

	@Autowired
	UserAdressesRepository userAdressesRepository;
	@Override
	public UserAdresses createUserAdresses(@Valid UserAdresses useradd) {
		
		return userAdressesRepository.save(useradd);
	}
	@Override
	public List<UserAdresses> getAllAdresses() {
		// TODO Auto-generated method stub
		return userAdressesRepository.findAll();
	}

}
