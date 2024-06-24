package com.example.communicationlog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.communicationlog.model.CommunicationLog;

@Repository
public interface CommunicationLogRepository extends JpaRepository<CommunicationLog, Integer>{
	
	Optional<CommunicationLog> findBySalesRepresentativeId(long salesRepresentativeId);
	List<CommunicationLog> findAll();
	Optional<CommunicationLog> findByActivityId(Integer actvityId);
	Optional<CommunicationLog> findByEnquiryId(Integer enquiryId);

}
