package com.example.usermanagement.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usermanagement.model.UserEducationDetails;

@Repository
public interface UserEducationDetailsRepository extends JpaRepository<UserEducationDetails, Integer>{

	void save(List<UserEducationDetails> userEducationDetails);

}
