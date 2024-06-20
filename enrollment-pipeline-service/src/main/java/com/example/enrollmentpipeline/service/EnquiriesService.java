package com.example.enrollmentpipeline.service;

import java.util.List;

import com.example.enrollmentpipeline.model.Courses;
import com.example.enrollmentpipeline.model.Enquiries;
import com.example.enrollmentpipeline.model.PipeLinePhases;

public interface EnquiriesService {
	
	Enquiries getEnquiryById(int id);
	    List<Enquiries> getAllEnquiries();
	    Enquiries updateEnquiry(Enquiries enquiry,int id);
	   
	    void deleteEnquiry(int id);
	    
		Enquiries saveEnquiry(Enquiries enquiry, int id, int pipeLinePhaseId);
}
