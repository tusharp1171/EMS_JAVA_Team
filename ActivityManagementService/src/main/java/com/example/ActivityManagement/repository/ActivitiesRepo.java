package com.example.ActivityManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ActivityManagement.model.Activities;

@Repository
public interface ActivitiesRepo extends JpaRepository<Activities, Integer>{

}
