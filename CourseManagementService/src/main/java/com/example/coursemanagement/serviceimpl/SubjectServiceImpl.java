package com.example.coursemanagement.serviceimpl;

import com.example.coursemanagement.customexception.EntityNotFoundException;
import com.example.coursemanagement.model.Subject;
import com.example.coursemanagement.repository.SubjectRepository;
import com.example.coursemanagement.service.SubjectService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {
	@Autowired
    private SubjectRepository subjectRepository;

    

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Integer id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + id));
    }

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(Integer id, Subject subject) {
        Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + id));

        existingSubject.setSubjectName(subject.getSubjectName());
        existingSubject.setDescription(subject.getDescription());

        return subjectRepository.save(existingSubject);
    }

    @Override
    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }
}