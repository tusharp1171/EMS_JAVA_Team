package com.example.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.usermanagement.model.UserAdresses;

import jakarta.transaction.Transactional;

@Repository
public interface UserAdressesRepository extends JpaRepository<UserAdresses, Integer>{
//	 @Modifying
//	    @Transactional
//	    @Query(value = "DELETE FROM user_address WHERE user_id = :userId", nativeQuery = true)
//	    void deleteByUserId();
//
	
	List<UserAdresses> findByUserId(Long userId);
}
