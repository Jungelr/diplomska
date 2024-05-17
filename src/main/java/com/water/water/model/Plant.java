package com.water.water.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Plant {

	@Id
	private Long id;

	private String name;
	private String description;
	private Integer minWatering;
	private Integer maxWatering;
}
