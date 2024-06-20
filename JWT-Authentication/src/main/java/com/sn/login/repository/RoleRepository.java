package com.sn.login.repository;

import java.util.Optional;

import com.sn.login.entity.ERole;
import com.sn.login.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}