package com.example.enrollmentpipeline.dto;

import java.util.List;

import com.example.enrollmentpipeline.model.Courses;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CourseSubjectMapping {

	private int courseId;

	private int subjectId;

}
