package com.example.enrollmentpipeline.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enrollmentpipeline.customexception.EntityNotFoundException;
import com.example.enrollmentpipeline.model.Courses;
import com.example.enrollmentpipeline.model.PipeLinePhases;
import com.example.enrollmentpipeline.repository.PipeLinePhasesRepository;
import com.example.enrollmentpipeline.service.PipeLinePhasesService;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class PipeLinePhasesServiceImpl implements PipeLinePhasesService {
	@Autowired
	private PipeLinePhasesRepository pipeLinePhasesRepository;

	@Override
	public PipeLinePhases savePhase(PipeLinePhases phase) {
		return pipeLinePhasesRepository.save(phase);
	}

	@Override
	public PipeLinePhases getPhaseById(int id) {
		return pipeLinePhasesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PipeLinePhases not found with id: " +id));
	}

	@Override
	public List<PipeLinePhases> getAllPhases() {
		return pipeLinePhasesRepository.findAll();
	}

	public PipeLinePhases updatePhase(PipeLinePhases phase, int id) {
		PipeLinePhases p = pipeLinePhasesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PipeLinePhases not found with id: " + id));
		p.setPipeLinePhaseId(phase.getPipeLinePhaseId());
		p.setPhaseName(phase.getPhaseName());
		return p;

	}

	@Override
	public void deletePhase(int id) {
		pipeLinePhasesRepository.deleteById(id);
	}
}
