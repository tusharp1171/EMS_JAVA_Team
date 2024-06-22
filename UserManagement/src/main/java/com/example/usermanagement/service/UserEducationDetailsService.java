package com.example.usermanagement.service;

import java.util.List;

import com.example.usermanagement.model.UserEducationDetails;

import jakarta.validation.Valid;

public interface UserEducationDetailsService {

	UserEducationDetails addEducationDetailsService(@Valid UserEducationDetails userEducationDetailslist);

	void deleteUserEducationDetailsByUserId(Long userId);

}
