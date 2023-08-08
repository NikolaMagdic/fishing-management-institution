package com.example.fishingmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fishingmanagementbackend.dto.FishingAreaDTO;
import com.example.fishingmanagementbackend.model.FishingArea;
import com.example.fishingmanagementbackend.service.FishingAreaService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/fishing-area")
public class FishingAreaController {

    @Autowired
    private FishingAreaService fishingAreaService;
    
    @GetMapping
    public ResponseEntity<List<FishingAreaDTO>> getFishingAreas() {
        
        List<FishingAreaDTO> fishingAreaDTOs = fishingAreaService.getFishingAreas();
        return ResponseEntity.ok().body(fishingAreaDTOs);
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FishingAreaDTO> getFishingArea(@PathVariable(value = "id") Long id ) {
        
        FishingAreaDTO fishingAreaDTO;
        try {
            fishingAreaDTO = fishingAreaService.getFishingArea(id);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok().body(fishingAreaDTO);
    }
    
    @PostMapping
    public ResponseEntity<FishingAreaDTO> createFishingArea(@RequestBody FishingAreaDTO fishingAreaDTO) {
        FishingArea fishingArea = fishingAreaService.createFishingArea(fishingAreaDTO);
        return ResponseEntity.status(201).body(new FishingAreaDTO(fishingArea));
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<FishingAreaDTO> updateFishingArea(@PathVariable("id") Long  id, @RequestBody FishingAreaDTO fishingAreaDTO) {
        FishingArea fishingArea;
        try {
            fishingArea = fishingAreaService.updateFishingArea(id, fishingAreaDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok().body(new FishingAreaDTO(fishingArea));
    }
    
    @PatchMapping("/{areaId}/add-fish-species/{speciesId}")
    public ResponseEntity<Boolean> addFishSpeciesToFishingArea(@PathVariable("areaId") Long areaId, @PathVariable("speciesId") Long speciesId) {

        fishingAreaService.addFishSpeciesToArea(areaId, speciesId);
        return ResponseEntity.ok(true);
    }
    
    @PatchMapping("/{areaId}/remove-fish-species/{speciesId}")
    public ResponseEntity<Boolean> removeFishSpeciesFromFishingArea(@PathVariable("areaId") Long areaId, @PathVariable("speciesId") Long speciesId) {
        fishingAreaService.removeFishSpeciesFromArea(areaId, speciesId);
        return ResponseEntity.ok(true);
    }
}
