package com.example.usermanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermanagement.exception.UserNotFoundException;
import com.example.usermanagement.model.UserAdresses;
import com.example.usermanagement.model.UserEducationDetails;
import com.example.usermanagement.repository.UserEducationDetailsRepository;
import com.example.usermanagement.service.UserEducationDetailsService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class UserEducationDetailsServiceImpl implements UserEducationDetailsService {
	@Autowired
	UserEducationDetailsRepository educationDetailsRepository;

	@Override
	public UserEducationDetails addEducationDetailsService(UserEducationDetails userEducationDetails) {
		// TODO Auto-generated method stub
		return educationDetailsRepository.save(userEducationDetails);
	}

	@Override
	@Transactional
	public void deleteUserEducationDetailsByUserId(Long userId) throws UserNotFoundException {

		List<UserEducationDetails> educationDetailslist = educationDetailsRepository.findByUserId(userId);
		if (educationDetailslist.isEmpty()) {
			throw new UserNotFoundException("No addresses found for user ID: " + userId);
		}
		for (UserEducationDetails educationDetails : educationDetailslist) {
			educationDetailsRepository.deleteById(educationDetails.getId());
		}
	}

}
