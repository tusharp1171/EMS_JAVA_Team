package com.example.enrollmentpipeline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enrollmentpipeline.model.Courses;
import com.example.enrollmentpipeline.serviceimpl.CoursesServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Courses")
public class Coursescontroller {
	@Autowired
	CoursesServiceImpl coursesServiceImpl;

	@PostMapping()
	public Courses SaveCourses(@RequestBody @Valid Courses courses) {
		return coursesServiceImpl.SaveCourses(courses);

	}

	@GetMapping("/{Id}")
	public Courses getCourseById(@PathVariable int Id) {
		return coursesServiceImpl.getCourseById(Id);
	}

	@GetMapping
	public List<Courses> getAllCourses() {
		return coursesServiceImpl.getAllCourses();

	}

	@PutMapping("/{Id}")
	public Courses updateCourses(@RequestBody @Valid Courses courses, @PathVariable int Id) {
		return coursesServiceImpl.updateCourses(courses, Id);
	}

	@DeleteMapping("/{Id}")
	public String deleteCourses(@PathVariable int Id) {
		return coursesServiceImpl.deleteCourses(Id);
	}
}
