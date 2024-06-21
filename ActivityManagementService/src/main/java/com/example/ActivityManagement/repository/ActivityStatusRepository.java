package com.example.ActivityManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ActivityManagement.model.ActivityStatus;

public interface ActivityStatusRepository extends JpaRepository<ActivityStatus, Integer> {

	ActivityStatus save(String statusName);

}
