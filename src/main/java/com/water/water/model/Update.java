package com.water.water.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MICRO_CONTROLLER_UPDATE")
@Getter
@Setter
@NoArgsConstructor
public class Update {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String hash;

  private String filename;

  private byte[] update;
}
