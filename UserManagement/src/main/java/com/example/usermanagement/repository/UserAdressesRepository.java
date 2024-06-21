package com.example.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usermanagement.model.UserAdresses;

@Repository
public interface UserAdressesRepository extends JpaRepository<UserAdresses, Integer>{

}
