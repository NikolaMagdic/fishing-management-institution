package com.example.fishingmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fishingmanagementbackend.dto.FishingSpotDTO;
import com.example.fishingmanagementbackend.model.FishingSpot;
import com.example.fishingmanagementbackend.service.FishingSpotService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/fishing-spot")
public class FishingSpotController {

    @Autowired
    private FishingSpotService fishingSpotService;
    
    @GetMapping("/all/{areaId}")
    public ResponseEntity<List<FishingSpotDTO>> getAllFishingSpotsInArea(@PathVariable("areaId") Long areaId) {
        List<FishingSpotDTO> fishingSpots;
        try {
            fishingSpots = fishingSpotService.getAllFishingSpotsInArea(areaId);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fishingSpots);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FishingSpotDTO> getFishingSpotById(@PathVariable("id") Long id) {
        FishingSpotDTO fishingSpotDTO;
        try {
            fishingSpotDTO = fishingSpotService.getFishingSpotById(id);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fishingSpotDTO);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FishingSpotDTO> addNewFishingSpot(@RequestBody FishingSpotDTO fishingSpotDTO) {
           FishingSpot fishingSpot;
        try {
            fishingSpot = fishingSpotService.addFishingSpotToArea(fishingSpotDTO);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(201).body(new FishingSpotDTO(fishingSpot));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FishingSpotDTO> updateFishingSpot(@PathVariable("id}") Long id, @RequestBody FishingSpotDTO fishingSpotDTO) {
        FishingSpot fishingSpot;
        try {
            fishingSpot = fishingSpotService.updateFishingSpot(id, fishingSpotDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new FishingSpotDTO(fishingSpot));
    }
}
