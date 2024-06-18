package com.water.water.service.impl;

import com.water.water.mappers.PlantDataMapper;
import com.water.water.mappers.PlantStateMapper;
import com.water.water.model.Plant;
import com.water.water.model.PlantState;
import com.water.water.model.dtos.PlantDto;
import com.water.water.model.dtos.PlantStateDto;
import com.water.water.repository.PlantDataRepository;
import com.water.water.repository.PlantStateRepository;
import com.water.water.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {

	private final PlantDataRepository plantDataRepository;
	private final PlantDataMapper plantDataMapper;
	private final PlantStateRepository plantStateRepository;
	private final PlantStateMapper plantStateMapper;

	@Override
	public List<PlantDto> getAllPlants() {

		return plantDataRepository
				.findAll()
				.stream()
				.map(plantDataMapper::plantToPlantDto)
				.collect(Collectors.toList());
	}

	@Override
	public PlantDto addPlant(PlantDto plantDto) {

		Plant newPlant = plantDataMapper.plantDtoToPlant(plantDto);
		Plant persistedPlant = plantDataRepository.save(newPlant);
		return plantDataMapper.plantToPlantDto(persistedPlant);
	}

	@Override
	public PlantDto getPlant(String id) {
		return plantDataRepository
				.findById(id)
				.map(plantDataMapper::plantToPlantDto)
				.orElseThrow();
	}

	@Override
	public PlantStateDto getPlantState(String id) {
		return plantStateRepository
				.findById(id)
				.map(plantStateMapper::mapFromEntity)
				.orElseThrow();
	}

	@Override
	public void savePlantState(PlantStateDto plantStateDto) {

		PlantState plantStateEntity = plantStateMapper.mapToEntity(plantStateDto);

		plantStateRepository.save(plantStateEntity);
	}
}
