package com.water.water.mappers;

import com.water.water.model.Plant;
import com.water.water.model.dtos.PlantDto;
import org.mapstruct.Mapper;

@Mapper
public interface PlantMapper {

	PlantDto plantToPlantDto(Plant plant);

	Plant plantDtoToPlant(PlantDto plantDto);
}
