package com.example.fishingmanagementbackend.controller;

import java.time.LocalDate;
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

import com.example.fishingmanagementbackend.dto.PenaltyDTO;
import com.example.fishingmanagementbackend.service.PenaltyService;

@RestController
@RequestMapping("api/penalty")
public class PenaltyController {

    @Autowired
    private PenaltyService penaltyService;
    
    @GetMapping("/{id}")
    public ResponseEntity<PenaltyDTO> getPenaltyById(@PathVariable("id") Long id) {
        PenaltyDTO penalty = penaltyService.getPenaltyById(id);
        return ResponseEntity.ok(penalty);
    }
    
    @GetMapping
    public ResponseEntity<List<PenaltyDTO>> getAllPenalties() {
        List<PenaltyDTO> penalties = penaltyService.getAllPenalties();
        return ResponseEntity.ok(penalties);
    }
    
    @GetMapping("/fisherman/{fishermanId}")
    public ResponseEntity<List<PenaltyDTO>> getAllPenaltiesOfFisherman(@PathVariable("fishermanId") Long fishermanId) {
        List<PenaltyDTO> penalties = penaltyService.getAllPenaltiesOfFisherman(fishermanId);
        return ResponseEntity.ok(penalties);
    }
    
    @PostMapping
    public ResponseEntity<PenaltyDTO> addPenalty(@RequestBody PenaltyDTO penaltyDTO) {
        PenaltyDTO penalty = penaltyService.addPenalty(penaltyDTO);
        return ResponseEntity.ok(penalty);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PenaltyDTO> updatePenalty(@PathVariable("id") Long id, @RequestBody PenaltyDTO penaltyDTO) {
        PenaltyDTO penalty = penaltyService.updatePenalty(id, penaltyDTO);
        return ResponseEntity.ok(penalty);
    }
    
    @PatchMapping("/{penaltyId}/impose/{fishermanId}")
    public ResponseEntity<?> imposeAPenalty(@PathVariable("penaltyId") Long penaltyId, @PathVariable("fishermanId") Long fishermanId) {
        boolean p = penaltyService.imposeAPenalty(penaltyId, fishermanId, LocalDate.now());
        return ResponseEntity.ok(p);
    }
    
}
