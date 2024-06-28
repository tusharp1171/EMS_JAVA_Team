package com.example.rolefeature.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rolefeature.Model.Features;

@Repository
public interface FeaturesRepository extends JpaRepository<Features, Long>{

}
