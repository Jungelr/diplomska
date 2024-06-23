package com.water.water.service;

import com.water.water.model.dtos.PlantDto;
import com.water.water.model.dtos.PlantStateDto;

import java.util.List;

public interface PlantService {

	List<PlantDto> getAllPlants();

	PlantDto addPlant(PlantDto plantDto);

	PlantDto getPlant(String id);

	PlantStateDto getPlantState(String id);

	void savePlantState(PlantStateDto plantStateDto);

	void deletePlant(String id);
}
