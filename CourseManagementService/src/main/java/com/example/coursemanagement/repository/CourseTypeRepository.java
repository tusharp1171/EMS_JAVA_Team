package com.example.coursemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coursemanagement.model.CourseType;

@Repository
public interface CourseTypeRepository extends JpaRepository<CourseType,Integer>{
	List<CourseType> findByTypeName(String typeName);
}
