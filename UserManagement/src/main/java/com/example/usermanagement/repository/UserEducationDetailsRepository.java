package com.example.usermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usermanagement.model.UserEducationDetails;

public interface UserEducationDetailsRepository extends JpaRepository<UserEducationDetails, Integer>{

	List<UserEducationDetails> findByUserId(Long userId);

}
