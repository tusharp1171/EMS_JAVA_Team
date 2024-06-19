package com.example.coursemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coursemanagement.model.Syllabus;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Integer>{
	
	 List<Syllabus> findByCourseType_Id(Integer courseTypeId);
}
