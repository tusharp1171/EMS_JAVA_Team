package com.example.rolefeature.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rolefeature.Model.Features;
import com.example.rolefeature.ServiceImpl.FeaturesServicesImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping
public class FeaturesController {

	@Autowired
	FeaturesServicesImpl dataservices ;
	
	@GetMapping("/readFeatures")
	 public ResponseEntity<List<Features>> getAllFEatures() {
        List<Features> feature = dataservices.getAllFeatures();
        return new ResponseEntity<>(feature, HttpStatus.OK);
	}
	
	@GetMapping("/readFeatureById/{featureId}")
	public ResponseEntity<Features> getFeatureById(@PathVariable Long featureId ) {
		Features idFeature = dataservices.getFeatureById(featureId);
		return ResponseEntity.ok().body(idFeature);
	}	
	
	@PostMapping("/createFeature")
	public ResponseEntity<Features> createFeature(@Valid @RequestBody Features feature) {
		Features postFeature = dataservices.createFeature(feature);
		return ResponseEntity.ok().body(postFeature) ;
	}
	
	@PutMapping("/updateFeature/{id}")
	public ResponseEntity<Features> updateFeature(@Valid@PathVariable Long id, @RequestBody Features feature) {
		Features updateFeatures = dataservices.updateFeature(id, feature);
		return ResponseEntity.ok().body(updateFeatures);
		
	}
	
	@DeleteMapping("/deleteFeature")
	public ResponseEntity<Features> deleteFeatuer(@PathVariable Long id){
		Features delFeature = dataservices.deleteFeature(id);
		return ResponseEntity.ok().body(delFeature);
		
	}
	
}
