package com.example.rolefeature.Service;

import java.util.List;

import com.example.rolefeature.Model.FeatureRoleMapping;

public interface FeatureRoleMappingServices {

	FeatureRoleMapping saveMapping(FeatureRoleMapping entity);

	List<FeatureRoleMapping> getAllInfo();

	FeatureRoleMapping getRoleFeatureMapById(Long id);

	FeatureRoleMapping updateFeatureRoleMap(Long id, FeatureRoleMapping entity);
}
