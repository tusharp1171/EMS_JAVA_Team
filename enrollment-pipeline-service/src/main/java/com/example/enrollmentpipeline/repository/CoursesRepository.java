package com.example.enrollmentpipeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enrollmentpipeline.model.Courses;
@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

}
