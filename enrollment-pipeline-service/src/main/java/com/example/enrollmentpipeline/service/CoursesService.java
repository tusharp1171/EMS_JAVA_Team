package com.example.enrollmentpipeline.service;

import java.util.List;

import com.example.enrollmentpipeline.model.Courses;

public interface CoursesService {
	public Courses SaveCourses(Courses courses) ;
	public Courses getCourseById(int Id);
	public List<Courses> getAllCourses();
	public Courses updateCourses(Courses course, int Id) ;
	public String deleteCourses(int Id);

}
