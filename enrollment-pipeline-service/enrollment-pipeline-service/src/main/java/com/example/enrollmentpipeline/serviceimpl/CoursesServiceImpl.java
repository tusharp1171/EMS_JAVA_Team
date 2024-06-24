package com.example.enrollmentpipeline.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enrollmentpipeline.customexception.EntityNotFoundException;
import com.example.enrollmentpipeline.model.Courses;
import com.example.enrollmentpipeline.repository.CoursesRepository;
import com.example.enrollmentpipeline.service.CoursesService;

@Service
public class CoursesServiceImpl implements CoursesService {
	@Autowired
	private CoursesRepository coursesRepository;

	// crud

	
	public Courses SaveCourses(Courses courses) {
		return coursesRepository.save(courses);
	}

	public Courses getCourseById(int id) {
		return coursesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Courses not found with id: " + id));

	}

	public List<Courses> getAllCourses() {
		return coursesRepository.findAll();
	}

	public Courses updateCourses (Courses course, int id) {
		Courses course_temp = coursesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Courses not found with id: " + id));
		course_temp.setCourseName(course.getCourseName());
		course_temp.setDescription(course.getDescription());
		course_temp.setCourseFees(course.getCourseFees());
		course_temp.setCourseDuration(course.getCourseDuration());
//		course_temp.setCourseTypeId(course.getCourseTypeId());
		return course_temp;

	}

	public String deleteCourses(int Id) {
		coursesRepository.deleteById(Id);
		return "Deleted";
	}

}
