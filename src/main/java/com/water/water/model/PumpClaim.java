package com.water.water.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "PUMP_CLAIM")
@Getter
@Setter
@NoArgsConstructor
public class PumpClaim {

  @Id
  private String plantId;

  @UpdateTimestamp
  private LocalDateTime lastModified;
}
