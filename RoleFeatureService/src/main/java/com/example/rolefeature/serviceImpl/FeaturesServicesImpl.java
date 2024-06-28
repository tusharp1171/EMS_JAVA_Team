package com.example.rolefeature.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rolefeature.Model.Features;
import com.example.rolefeature.Repository.FeaturesRepository;
import com.example.rolefeature.Service.FeaturesServices;

@Service
public class FeaturesServicesImpl implements FeaturesServices{
	
	@Autowired
	FeaturesRepository featureRepo;

	@Override
	public List<Features> getAllFeatures() {
		List<Features> feature = this.featureRepo.findAll();
		return feature;
	}

	@Override
	public Features createFeature(Features feature) {
		Features postfeature = featureRepo.save(feature);
		return postfeature;
	}

	@Override
	public Features getFeatureById(Long featureId) {
		Optional<Features>idFeature = this.featureRepo.findById(featureId);
		if(idFeature.isPresent()) {
			return featureRepo.getById(featureId);
			
		}
		return null;

	}

	@Override
	public Features updateFeature(Long id, Features feature) {
		Optional<Features> features = this.featureRepo.findById(id);
		
		if (features.isPresent()) {

			Features stat = features.get();

			stat.setFeaturesDescriptoin(feature.getFeaturesDescriptoin());
			stat.setFreaturesName(feature.getFreaturesName());
			
	        return featureRepo.save(stat);
		}
		return null;
	}

	
	
	@Override
	public Features deleteFeature(Long id) {
		Optional<Features> delfeature = this.featureRepo.findById(id);
		if(delfeature.isPresent()) {
			this.featureRepo.deleteById(id);
		}
		return null;
	}
	
	
	
}
