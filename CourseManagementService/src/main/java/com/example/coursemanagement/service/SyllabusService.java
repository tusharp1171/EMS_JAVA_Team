package com.example.coursemanagement.service;

import java.util.List;

import com.example.coursemanagement.model.Syllabus;

public interface SyllabusService {
	 	List<Syllabus> getAllSyllabi();
	    
	    Syllabus getSyllabusById(Integer id);
	    
	   	    Syllabus updateSyllabus(Integer id, Syllabus syllabus);
	    
	    void deleteSyllabus(Integer id);

		Syllabus createSyllabus(Integer subjectId, Integer courseTypeId, String sectionName, String topicName);

		List<Syllabus> getSyllabiByCourseType(Integer courseTypeId);

}
