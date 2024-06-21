package com.example.ActivityManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ActivityManagement.model.ActivityType;

public interface ActivityTypeRepository  extends JpaRepository<ActivityType, Integer>{

}
