package com.example.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usermanagement.model.UserType;
@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer>{

	Optional<UserType> findById(Long id);

}
