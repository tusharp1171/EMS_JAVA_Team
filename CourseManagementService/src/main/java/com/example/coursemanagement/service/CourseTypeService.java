package com.example.coursemanagement.service;

import java.util.List;

import com.example.coursemanagement.model.CourseType;

public interface CourseTypeService {
	  List<CourseType> getAllCourseTypes();
	    
	    CourseType getCourseTypeById(Integer id);
	    
	    CourseType createCourseType(CourseType courseType);
	    
	    CourseType updateCourseType(Integer id, CourseType courseType);
	    
	    void deleteCourseType(Integer id);
}
