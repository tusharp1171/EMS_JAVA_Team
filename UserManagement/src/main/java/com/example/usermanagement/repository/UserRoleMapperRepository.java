package com.example.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usermanagement.model.UserRoleMapper;
@Repository
public interface UserRoleMapperRepository extends JpaRepository<UserRoleMapper, Integer> {

}
