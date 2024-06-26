package com.water.water.repository;

import com.water.water.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantDataRepository extends JpaRepository<Plant, String> { }
