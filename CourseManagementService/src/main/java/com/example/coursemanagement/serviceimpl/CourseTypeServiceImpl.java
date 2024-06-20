package com.example.coursemanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.coursemanagement.model.CourseType;
import com.example.coursemanagement.repository.CourseTypeRepository;
import com.example.coursemanagement.service.CourseTypeService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {
	@Autowired
	private  CourseTypeRepository courseTypeRepository;
	

    @Override
    public List<CourseType> getAllCourseTypes() {
        return courseTypeRepository.findAll();
    }

    @Override
    public CourseType getCourseTypeById(Integer id) {
        return courseTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course type not found with id: " + id));
    }

    @Override
    public CourseType createCourseType(CourseType courseType) {
        return courseTypeRepository.save(courseType);
    }

    @Override
    public CourseType updateCourseType(Integer id, CourseType courseType) {
        CourseType existingCourseType = courseTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course type not found with id: " + id));

        existingCourseType.setTypeName(courseType.getTypeName());
        existingCourseType.setDescription(courseType.getDescription());

        return courseTypeRepository.save(existingCourseType);
    }

    @Override
    public void deleteCourseType(Integer id) {
        courseTypeRepository.deleteById(id);
    }
}
