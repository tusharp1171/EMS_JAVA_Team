package com.example.communicationlog.repository;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event.ID;

import com.example.communicationlog.model.CommunicationLog;

@Repository
public interface CommunicationLogRepository extends JpaRepository<CommunicationLog, Integer>{

}
