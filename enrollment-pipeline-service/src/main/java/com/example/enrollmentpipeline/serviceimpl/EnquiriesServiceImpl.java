package com.example.enrollmentpipeline.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enrollmentpipeline.customexception.EntityNotFoundException;
import com.example.enrollmentpipeline.model.Courses;
import com.example.enrollmentpipeline.model.Enquiries;
import com.example.enrollmentpipeline.model.PipeLinePhases;
import com.example.enrollmentpipeline.repository.CoursesRepository;
import com.example.enrollmentpipeline.repository.EnquiriesRepository;
import com.example.enrollmentpipeline.repository.PipeLinePhasesRepository;
import com.example.enrollmentpipeline.service.EnquiriesService;

@Service
public class EnquiriesServiceImpl implements EnquiriesService {
	@Autowired
	private EnquiriesRepository enquiriesRepository;
	@Autowired
	private CoursesRepository coursesRepository;
	@Autowired
	private PipeLinePhasesRepository pipeLinePhasesRepository;

	@Override
	public Enquiries getEnquiryById(int id) {
		return enquiriesRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Enquiry not found with id: " + id));
	}

	@Override
	public List<Enquiries> getAllEnquiries() {
		return enquiriesRepository.findAll();
	}

	@Override
	public Enquiries updateEnquiry(Enquiries enquiry, int id) {
		Enquiries enquiry_temp = enquiriesRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Enquiry not found with id: " + id));
		enquiry_temp.setEnquiryId(enquiry.getEnquiryId());
		enquiry_temp.setName(enquiry.getName());
		enquiry_temp.setEmail(enquiry.getEmail());
		enquiry_temp.setMobileNo(enquiry.getMobileNo());
		enquiry_temp.setEnquirySource(enquiry.getEnquirySource());
		return enquiry_temp;

	}

	@Override
	public void deleteEnquiry(int id) {
		enquiriesRepository.deleteById(id);
	}

	@Override
	public Enquiries saveEnquiry(Enquiries enquiry, int courseId, int pipeLinePhaseId) {
	    Courses course = coursesRepository.findById(courseId)
	            .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
	    PipeLinePhases pipeLinePhase = pipeLinePhasesRepository.findById(pipeLinePhaseId)
	            .orElseThrow(() -> new EntityNotFoundException("Pipeline phase not found with id " + pipeLinePhaseId));
	    
	    // Set the associations
	    enquiry.setCourses(course);
	    enquiry.setPipeLinePhases(pipeLinePhase);
	    
	    // Save the enquiry
	    return enquiriesRepository.save(enquiry);
	}

}
