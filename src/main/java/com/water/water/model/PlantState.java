package com.water.water.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PLANT_STATE")
@Getter
@Setter
@NoArgsConstructor
public class PlantState {

  @Id
  private String id;

  private String state;
}
