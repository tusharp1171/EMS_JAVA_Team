package com.example.enrollmentpipeline.service;

import java.util.List;

import com.example.enrollmentpipeline.model.PipeLinePhases;

public interface PipeLinePhasesService {
	 PipeLinePhases savePhase(PipeLinePhases phase);
	    PipeLinePhases getPhaseById(int id);
	    List<PipeLinePhases> getAllPhases();
	    PipeLinePhases updatePhase(PipeLinePhases phase, int id) ;
	    void deletePhase(int id);
}
