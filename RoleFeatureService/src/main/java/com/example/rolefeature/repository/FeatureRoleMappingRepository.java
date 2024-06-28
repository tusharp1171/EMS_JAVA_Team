package com.example.rolefeature.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rolefeature.Model.FeatureRoleMapping;

@Repository
public interface FeatureRoleMappingRepository extends JpaRepository<FeatureRoleMapping, Long> {

}
