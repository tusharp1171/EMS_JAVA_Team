package com.example.rolefeature.Service;

import java.util.List;

import com.example.rolefeature.Model.Features;

public interface FeaturesServices {

	List<Features> getAllFeatures();

	Features createFeature(Features feature);

	Features getFeatureById(Long featureId);

	Features updateFeature(Long id, Features feature);

	Features deleteFeature(Long id);
}
