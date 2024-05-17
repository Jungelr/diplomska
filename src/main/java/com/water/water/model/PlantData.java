package com.water.water.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
public class PlantData {

	@Id
	private Long id;
	private Long plantId;
	private BigDecimal moisture;
	private LocalDateTime timestamp;
}
