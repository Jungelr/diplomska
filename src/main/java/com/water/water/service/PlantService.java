package com.water.water.service;

import com.water.water.model.dtos.PlantDto;

import java.util.List;

public interface PlantService {

	List<PlantDto> getAllPlants();

	PlantDto addPlant(PlantDto plantDto);
}
