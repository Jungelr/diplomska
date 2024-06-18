package com.water.water.repository;

import com.water.water.model.PlantState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantStateRepository extends JpaRepository<PlantState, String> { }