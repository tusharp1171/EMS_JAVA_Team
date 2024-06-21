package com.example.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usermanagement.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

}
