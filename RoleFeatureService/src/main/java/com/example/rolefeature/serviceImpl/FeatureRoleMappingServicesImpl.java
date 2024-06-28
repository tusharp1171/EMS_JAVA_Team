package com.example.rolefeature.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rolefeature.Model.FeatureRoleMapping;
import com.example.rolefeature.Repository.FeatureRoleMappingRepository;
import com.example.rolefeature.Service.FeatureRoleMappingServices;

@Service
public class FeatureRoleMappingServicesImpl implements FeatureRoleMappingServices {

	@Autowired
	FeatureRoleMappingRepository frmRepo;

//	@Override
//	public FeatureRoleMapping saveMapping(FeatureRoleMapping entity) {
//		FeatureRoleMapping saveMap = frmRepo.save(entity);
//		return saveMap;	
//	}
	
	@Override
    public FeatureRoleMapping saveMapping(FeatureRoleMapping entity) {
        return frmRepo.save(entity);
    }


	@Override
	public List<FeatureRoleMapping> getAllInfo() {
		List<FeatureRoleMapping> read = this.frmRepo.findAll();
		return read;
	}


	@Override
	public FeatureRoleMapping getRoleFeatureMapById(Long id) {
		
		Optional<FeatureRoleMapping> readById = this.frmRepo.findById(id);
		if(readById.isPresent()) {
			return frmRepo.getById(id);
		}
		return null;
	}


	@Override
	public FeatureRoleMapping updateFeatureRoleMap(Long id, FeatureRoleMapping entity) {
		
		Optional<FeatureRoleMapping> opt = this.frmRepo.findById(id);
		if(opt.isPresent()) {
			
			FeatureRoleMapping featureRole = opt.get();
			
			featureRole.setRoleId(entity.getRoleId());
//			featureRole.setFeature(entity.getFeature());
			
			return frmRepo.save(featureRole);
			
		}
		
		return null;
	}
	
	
	
	
	
}
