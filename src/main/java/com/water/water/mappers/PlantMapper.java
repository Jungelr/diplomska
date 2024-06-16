package com.water.water.mappers;

import com.water.water.model.Plant;
import com.water.water.model.dtos.PlantDto;
import org.apache.catalina.util.StringUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.thymeleaf.util.StringUtils;

import java.util.UUID;

@Mapper
public interface PlantMapper {

	PlantDto plantToPlantDto(Plant plant);

	@Mapping(target = "id", source = "id", qualifiedByName = "generateUUID")
	Plant plantDtoToPlant(PlantDto plantDto);

	@Named("generateUUID")
	default String generateUUID(String id) {
		if (StringUtils.isEmpty(id)) {
			return String.valueOf(UUID.randomUUID());
		}
		return id;
	}
}
