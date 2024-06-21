package com.example.usermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usermanagement.model.UserCredential;
@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {

	void save(List<UserCredential> userCredential);

}
