package com.water.water.controller;

import com.water.water.model.dtos.PlantDto;
import com.water.water.model.dtos.PlantStateDto;
import com.water.water.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
public class PlantRestController {

  private final PlantService plantService;

  @GetMapping("/data/{id}")
  public ResponseEntity<PlantDto> getPlantData(@PathVariable String id) {

    return ResponseEntity.ok(plantService.getPlant(id));
  }

  @GetMapping("/state/{id}")
  public ResponseEntity<PlantStateDto> getPlantState(@PathVariable String id) {

    return ResponseEntity.ok(plantService.getPlantState(id));
  }

  @PostMapping("/state")
  public ResponseEntity<Void> savePlantState(@RequestBody PlantStateDto plantStateDto) {

    plantService.savePlantState(plantStateDto);

    return ResponseEntity.noContent().build();
  }
}
