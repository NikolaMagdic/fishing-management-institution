package com.example.fishingmanagementbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fishingmanagementbackend.dto.FishPopulationModificationRequestDTO;
import com.example.fishingmanagementbackend.dto.FishPopulationModificationResponseDTO;
import com.example.fishingmanagementbackend.model.FishPopulationModification;
import com.example.fishingmanagementbackend.service.FishPopulationModificationService;

@RestController
@RequestMapping("/api/fish-population-modification")
public class FishPopulationModificationController {

    @Autowired
    private FishPopulationModificationService fishPopulationModificationService;
    
    @GetMapping
    public ResponseEntity<List<FishPopulationModificationResponseDTO>> getAllFishPopulationModifications() {
        List<FishPopulationModificationResponseDTO> modificationsDTO = fishPopulationModificationService.getAllFishPopulationModifications();
        return ResponseEntity.ok(modificationsDTO);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('KEEPER')")
    public ResponseEntity<FishPopulationModificationResponseDTO> makeFishPopulationModification(@RequestBody FishPopulationModificationRequestDTO fishPopulationModificationRequest) {
        FishPopulationModification modification = fishPopulationModificationService.makeModification(fishPopulationModificationRequest);
        FishPopulationModificationResponseDTO fishPopulationModificationResponse = new FishPopulationModificationResponseDTO(modification);
        return ResponseEntity.ok(fishPopulationModificationResponse);
    }
}
