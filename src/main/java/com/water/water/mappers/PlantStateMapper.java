package com.water.water.mappers;

import com.water.water.model.PlantState;
import com.water.water.model.dtos.PlantStateDto;
import org.mapstruct.Mapper;

@Mapper
public interface PlantStateMapper {

  PlantStateDto mapFromEntity(PlantState plantState);
  PlantState mapToEntity(PlantStateDto plantStateDto);
}
