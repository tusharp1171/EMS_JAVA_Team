package com.example.coursemanagement.service;

import java.util.List;

import com.example.coursemanagement.model.Subject;

public interface SubjectService {
	
	List<Subject> getAllSubjects();
    
    Subject getSubjectById(Integer id);
    
    Subject createSubject(Subject subject);
    
    Subject updateSubject(Integer id, Subject subject);
    
    void deleteSubject(Integer id);

}
