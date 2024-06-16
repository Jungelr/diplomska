package com.water.water.service.impl;

import com.water.water.mappers.PlantMapper;
import com.water.water.model.Plant;
import com.water.water.model.dtos.PlantDto;
import com.water.water.repository.PlantRepository;
import com.water.water.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {

	private final PlantRepository plantRepository;
	private final PlantMapper plantMapper;

	@Override
	public List<PlantDto> getAllPlants() {

		return plantRepository
				.findAll()
				.stream()
				.map(plantMapper::plantToPlantDto)
				.collect(Collectors.toList());
	}

	@Override
	public PlantDto addPlant(PlantDto plantDto) {

		Plant newPlant = plantMapper.plantDtoToPlant(plantDto);
		Plant persistedPlant = plantRepository.save(newPlant);
		return plantMapper.plantToPlantDto(persistedPlant);
	}

//	private List<PlantDto> mockData() {
//
//		PlantDto plantDto1 = new PlantDto() {
//			{
//				setId(String.valueOf(UUID.randomUUID()));
//				setName("Plant 1");
//				setDescription("Description 1");
//			}
//		};
//
//		PlantDto plantDto2 = new PlantDto() {
//			{
//				setId(String.valueOf(UUID.randomUUID()));
//				setName("Plant 2");
//				setDescription("Description 2");
//			}
//		};
//
//		PlantDto plantDto3 = new PlantDto() {
//			{
//				setId(String.valueOf(UUID.randomUUID()));
//				setName("Plant 3");
//				setDescription("Description 3");
//			}
//		};
//
//		return List.of(plantDto1, plantDto2, plantDto3);
//	}
}
