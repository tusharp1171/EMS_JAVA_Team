package com.example.usermanagement.service;

import java.util.List;

import com.example.usermanagement.model.Users;

import jakarta.validation.Valid;

public interface UserService {
    List<Users> getAllUsers();
    Users getUserById(int id);
    Users createUser(@Valid Users user);
    Users updateUser(@Valid Users user, int id);
    Users deleteUserRecord(int id);
	//UserAdresses createUserAdresses(@Valid UserAdresses useradd);
}
