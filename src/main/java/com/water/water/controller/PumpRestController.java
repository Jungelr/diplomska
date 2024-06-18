package com.water.water.controller;

import com.water.water.model.dtos.PumpAccessDto;
import com.water.water.service.PumpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pump")
@RequiredArgsConstructor
public class PumpRestController {

  private final PumpService pumpService;

  @PostMapping("/acquire/{id}")
  public ResponseEntity<PumpAccessDto> acquirePump(@PathVariable String id) {

    PumpAccessDto pumpAccessDto = pumpService.acquirePump(id);
    return ResponseEntity.ok(pumpAccessDto);
  }

  @PostMapping("/release/{id}")
  public ResponseEntity<Void> releasePump(@PathVariable String id) {

    pumpService.releasePump(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/hasClaim/{id}")
  public ResponseEntity<PumpAccessDto> hasClaimOfPump(@PathVariable String id) {

    PumpAccessDto pumpAccessDto = pumpService.hasClaim(id);
    return ResponseEntity.ok(pumpAccessDto);
  }
}
