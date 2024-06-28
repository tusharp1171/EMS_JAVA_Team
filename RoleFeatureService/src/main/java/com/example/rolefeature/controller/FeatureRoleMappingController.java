package com.example.rolefeature.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rolefeature.Model.FeatureRoleMapping;
import com.example.rolefeature.Service.FeatureRoleMappingServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class FeatureRoleMappingController {

	@Autowired
	FeatureRoleMappingServices services;
	
//	@PostMapping("/saveFRMapping")
//	public ResponseEntity<FeatureRoleMapping> saveFRMapping(@Valid@RequestBody FeatureRoleMapping entity) {
//		
//		FeatureRoleMapping saveMap  = this.services.saveMapping(entity);
//		
//		return ResponseEntity.ok().body(saveMap);
//	}
	
	@PostMapping("/saveFRMapping")
    public ResponseEntity<FeatureRoleMapping> saveFRMapping(@Valid @RequestBody FeatureRoleMapping entity) {
        FeatureRoleMapping saveMap = services.saveMapping(entity);
        return ResponseEntity.ok().body(saveMap);
	}
        
        
	@GetMapping("/readFRMap")
	public ResponseEntity<List<FeatureRoleMapping>> readFRMap () {
			List<FeatureRoleMapping> rMap = this.services.getAllInfo();
		return ResponseEntity.ok().body(rMap);
	}
	
	@GetMapping("/readFeatureById/{id}")
	public ResponseEntity<FeatureRoleMapping> getMapById(@PathVariable Long id) {
		FeatureRoleMapping idRFeature = services.getRoleFeatureMapById(id);
		return ResponseEntity.ok().body(idRFeature);
	}	
	
	@PutMapping("/updateFRMap/{id}")
	public ResponseEntity<FeatureRoleMapping> updateFRMap(@PathVariable Long id, @RequestBody  FeatureRoleMapping entity) {
		
		FeatureRoleMapping upMap = this.services.updateFeatureRoleMap(id, entity);
		return ResponseEntity.ok().body(upMap);
	}
	
	
}

