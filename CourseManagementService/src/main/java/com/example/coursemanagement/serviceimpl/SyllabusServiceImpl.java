package com.example.coursemanagement.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coursemanagement.model.CourseType;
import com.example.coursemanagement.model.Subject;
import com.example.coursemanagement.model.Syllabus;
import com.example.coursemanagement.repository.CourseTypeRepository;
import com.example.coursemanagement.repository.SubjectRepository;
import com.example.coursemanagement.repository.SyllabusRepository;
import com.example.coursemanagement.service.SyllabusService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SyllabusServiceImpl implements SyllabusService {
	@Autowired
    private  SyllabusRepository syllabusRepository;


    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseTypeRepository courseTypeRepository;

  
    
    @Override
    public List<Syllabus> getAllSyllabi() {
        return syllabusRepository.findAll();
    }

    @Override
    public Syllabus getSyllabusById(Integer id) {
        return syllabusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Syllabus not found with id: " + id));
    }

  
//    public Syllabus createSyllabus(Integer subjectId, Integer courseTypeId, String sectionName, String topicName) {
//        // Retrieve Subject and CourseType entities
//        Subject subject = subjectRepository.findById(subjectId)
//                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + subjectId));
//
//        CourseType courseType = courseTypeRepository.findById(courseTypeId)
//                .orElseThrow(() -> new EntityNotFoundException("CourseType not found with id: " + courseTypeId));
//
//        // Create Syllabus entity and set properties
//        Syllabus syllabus = new Syllabus();
//        syllabus.setSubject(subject);
//        syllabus.setCourseType(courseType);
//        syllabus.setSectionName(sectionName);
//        syllabus.setTopicName(topicName);
//
//        // Save and return the created Syllabus entity
//        return syllabusRepository.save(syllabus);
//    }
    
    @Override
    public Syllabus createSyllabus(Integer subjectId, Integer courseTypeId, String sectionName, String topicName) {
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        Optional<CourseType> courseTypeOptional = courseTypeRepository.findById(courseTypeId);

        if (subjectOptional.isPresent() && courseTypeOptional.isPresent()) {
            Syllabus syllabus = new Syllabus();
            syllabus.setSubject(subjectOptional.get());
            syllabus.setCourseType(courseTypeOptional.get());
            syllabus.setSectionName(sectionName);
            syllabus.setTopicName(topicName);
            return syllabusRepository.save(syllabus);
        } else {
            throw new IllegalArgumentException("Subject or CourseType not found");
        }
    }

    @Override
    public Syllabus updateSyllabus(Integer id, Syllabus syllabus) {
        Syllabus existingSyllabus = syllabusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Syllabus not found with id: " + id));

        existingSyllabus.setSubject(syllabus.getSubject());
        existingSyllabus.setSectionName(syllabus.getSectionName());
        existingSyllabus.setTopicName(syllabus.getTopicName());
        existingSyllabus.setCourseType(syllabus.getCourseType());

        return syllabusRepository.save(existingSyllabus);
    }

    @Override
    public void deleteSyllabus(Integer id) {
        syllabusRepository.deleteById(id);
    }
    @Override
    public List<Syllabus> getSyllabiByCourseType(Integer courseTypeId) {
        List<Syllabus> syllabi = syllabusRepository.findByCourseType_Id(courseTypeId);
        for (Syllabus syllabus : syllabi) {
            Hibernate.initialize(syllabus.getCourseType());
            // Initialize other lazy-loaded relationships if necessary
        }
        return syllabi;
    }
}