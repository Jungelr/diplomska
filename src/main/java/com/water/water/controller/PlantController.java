package com.water.water.controller;

import com.water.water.model.dtos.PlantDto;
import com.water.water.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/plants")
    public String plants(Model model) {

        List<PlantDto> plants = plantService.getAllPlants();

        model.addAttribute("plants", plants);

        return "plants";
    }

    @GetMapping("/addPlant")
    public String addPlant() {

        return "plantForm";
    }

    @PostMapping("/addPlant")
    public String addPlant(PlantDto plantDto) {
        PlantDto _ = plantService.addPlant(plantDto);

        return "redirect:/plants";
    }

    @GetMapping("/editPlant/{id}")
    public String editPlant(Model model, @PathVariable String id) {
        PlantDto plantDto = plantService.getPlant(id);

        model.addAttribute("plant", plantDto);

        return "plantForm";
    }

    @PostMapping("/editPlant")
    public String editPlant(PlantDto plantDto) {

        PlantDto _ = plantService.addPlant(plantDto);

        return "redirect:/plants";
    }

    @PostMapping("/deletePlant/{id}")
    public String deletePlant(@PathVariable String id) {

        plantService.deletePlant(id);

        return "redirect:/plants";
    }

    @PostMapping("furtherInfo")
    public String furtherInfo() {
        return "furtherInfo";
    }
}

