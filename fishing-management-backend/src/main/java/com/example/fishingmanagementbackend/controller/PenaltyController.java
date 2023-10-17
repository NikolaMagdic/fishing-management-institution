package com.example.fishingmanagementbackend.controller;

import java.security.Principal;
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

import com.example.fishingmanagementbackend.dto.PenalizedDTO;
import com.example.fishingmanagementbackend.dto.PenaltyDTO;
import com.example.fishingmanagementbackend.service.PenalizedService;
import com.example.fishingmanagementbackend.service.PenaltyService;

@RestController
@RequestMapping("api/penalty")
public class PenaltyController {

    @Autowired
    private PenaltyService penaltyService;
    
    @Autowired
    private PenalizedService penalizedService;
    
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
    public ResponseEntity<List<PenalizedDTO>> getAllPenaltiesOfFisherman(@PathVariable("fishermanId") Long fishermanId) {
        List<PenalizedDTO> penalties = penalizedService.getAllPenaltiesOfFisherman(fishermanId);
        return ResponseEntity.ok(penalties);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PenaltyDTO> addPenalty(@RequestBody PenaltyDTO penaltyDTO) {
        PenaltyDTO penalty = penaltyService.addPenalty(penaltyDTO);
        return ResponseEntity.ok(penalty);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PenaltyDTO> updatePenalty(@PathVariable("id") Long id, @RequestBody PenaltyDTO penaltyDTO) {
        PenaltyDTO penalty = penaltyService.updatePenalty(id, penaltyDTO);
        return ResponseEntity.ok(penalty);
    }
    
    @PostMapping("/impose/")
    @PreAuthorize("hasRole('KEEPER')")
    public ResponseEntity<PenalizedDTO> imposeAPenalty(@RequestBody PenalizedDTO penalizedDTO, Principal principal) {
        PenalizedDTO p = penalizedService.imposeAPenalty(penalizedDTO, principal);
        return ResponseEntity.ok(p);
    }
    
}
