package com.example.enrollmentpipeline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enrollmentpipeline.model.PipeLinePhases;
import com.example.enrollmentpipeline.service.PipeLinePhasesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/phases")
public class PipeLinePhasesController {
	 @Autowired
	    private PipeLinePhasesService pipeLinePhasesService;

	    @PostMapping
	    public PipeLinePhases createPhase(@RequestBody @Valid PipeLinePhases phase) {
	        return pipeLinePhasesService.savePhase(phase);
	    }

	    @GetMapping("/{id}")
	    public PipeLinePhases getPhaseById(@PathVariable int id) {
	        return pipeLinePhasesService.getPhaseById(id);
	    }

	    @GetMapping("/")
	    public List<PipeLinePhases> getAllPhases() {
	        return pipeLinePhasesService.getAllPhases();
	    }

	    @PutMapping("/{id}")
	    public PipeLinePhases updatePhase(@RequestBody @Valid PipeLinePhases phase, @PathVariable int id) {
	        return pipeLinePhasesService.updatePhase(phase, id);
	    }

	    @DeleteMapping("/{id}")
	    public void deletePhase(@PathVariable int id) {
	        pipeLinePhasesService.deletePhase(id);
	    }
}
