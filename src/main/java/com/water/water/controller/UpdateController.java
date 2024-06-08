package com.water.water.controller;

import com.water.water.service.UpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/update")
public class UpdateController {

  private final UpdateService updateService;

  @PostMapping("/upload")
  @PreAuthorize("hasRole('JENKINS')")
  public ResponseEntity<Void> uploadUpdate(@RequestParam("file") MultipartFile file) {

    updateService.uploadUpdate(file.getResource());

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/latest")
  @PreAuthorize("hasRole('ARDUINO_USER')")
  public ResponseEntity<Resource> getLatestUpdate() {

    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"patch.bin\"")
        .body(updateService.getLatestUpdate());
  }

  @GetMapping("/latest/hash")
  @PreAuthorize("hasRole('ARDUINO_USER')")
  public ResponseEntity<String> getLatestUpdateHash() {

    return ResponseEntity.ok(updateService.getLatestUpdateHash());
  }
}
