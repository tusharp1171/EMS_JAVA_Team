package com.example.usermanagement.service;

import com.example.usermanagement.model.UserEducationDetails;

import jakarta.validation.Valid;

public interface UserEducationDetailsService {

	UserEducationDetails addEducationDetailsService(@Valid UserEducationDetails userEducationDetails);

	void deleteUserEducationDetailsByUserId(Long userId);

}
