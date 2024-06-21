package com.example.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.usermanagement.model.UserRoleMapper;
import com.example.usermanagement.model.Users;
import com.example.usermanagement.service.UserRoleMapperService;

@RestController
@RequestMapping("/userRoleMappings")  // Plural endpoint path
public class UserRoleMapperController {

    @Autowired
    UserRoleMapperService userRoleMapperService;
    
    @PostMapping("/createrole")  
    public UserRoleMapper createUserRoleMapper(@RequestBody UserRoleMapper userRoleMapper) {
        UserRoleMapper newUserRoleMapper = userRoleMapperService.createUserRoleMapping(userRoleMapper);
        return newUserRoleMapper;  
    }
    
    @GetMapping("/get")  
    public List<UserRoleMapper> getAllUserRoleMapper() {
        return userRoleMapperService.getAllUserRoleMapper();
    }
    @GetMapping("getmapper/{id}")
    public ResponseEntity<UserRoleMapper> getUserRolemapperById(@PathVariable int id) {
    	UserRoleMapper user = userRoleMapperService.getUserRoleMapperById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
