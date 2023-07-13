package com.example.fishingmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fishingmanagementbackend.dto.FishSpeciesDTO;
import com.example.fishingmanagementbackend.model.FishSpecies;
import com.example.fishingmanagementbackend.service.FishSpeciesService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/fish-species")
public class FishSpeciesController {

    @Autowired
    private FishSpeciesService fishSpeciesService;
    
    @GetMapping
    public ResponseEntity<List<FishSpeciesDTO>> getAllFishSpecies() {
        
        List<FishSpeciesDTO> fishSpeciesDTOs = fishSpeciesService.getAllFishSpecies();
        return ResponseEntity.ok().body(fishSpeciesDTOs);
    
    }
    
    @GetMapping("/area/{areaId}")
    public ResponseEntity<List<FishSpeciesDTO>> getAllFishSpeciesInFishingArea(@PathVariable("areaId") Long id) {
        
        List<FishSpeciesDTO> fishSpeciesDTOs = fishSpeciesService.getAllFishSpeciesinFishingArea(id);
        
        return ResponseEntity.ok(fishSpeciesDTOs);
    }
    
    @GetMapping("/outside-area/{areaId}")
    public ResponseEntity<List<FishSpeciesDTO>> getAllFishSpeciesNotInFishingArea(@PathVariable("areaId") Long id) {
        
        List<FishSpeciesDTO> fishSpeciesDTOs = fishSpeciesService.getAllFishSpeciesNotInFishingArea(id);
        return ResponseEntity.ok(fishSpeciesDTOs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FishSpeciesDTO> getFishSpecies(@PathVariable("id") Long id) {
        
        FishSpeciesDTO fishSpeciesDTO;
        try {
            fishSpeciesDTO = fishSpeciesService.getFishSpecies(id);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    
        return ResponseEntity.ok().body(fishSpeciesDTO);
    }
    
    @PostMapping
    public ResponseEntity<FishSpeciesDTO> createFishSpecies(@RequestBody FishSpeciesDTO fishSpeciesDTO) {
        FishSpecies fishSpecies = fishSpeciesService.createFishSpecies(fishSpeciesDTO);
        return ResponseEntity.status(201).body(new FishSpeciesDTO(fishSpecies));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FishSpeciesDTO> updateFishSpecies(@PathVariable("id") Long id, @RequestBody FishSpeciesDTO fishSpeciesDTO) {
        FishSpecies fishSpecies;
        try {
            fishSpecies = fishSpeciesService.updateFishSpecies(id, fishSpeciesDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok().body(new FishSpeciesDTO(fishSpecies));
    }
}
