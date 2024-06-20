package com.example.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usermanagement.model.UserType;

@Repository
public interface UserTypeRepo extends JpaRepository<UserType, Integer>{

}
