package com.example.facultytimetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.facultytimetable.model.TimeTable;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer>{

	
}
