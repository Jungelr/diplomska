package com.water.water.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlantDto {

	private String id;
	private String name;
	private String description;
	private Integer minWatering;
	private Integer maxWatering;
}

