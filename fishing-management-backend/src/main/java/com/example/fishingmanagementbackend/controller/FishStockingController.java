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

import com.example.fishingmanagementbackend.dto.FishStockingRequestDTO;
import com.example.fishingmanagementbackend.dto.FishStockingResponseDTO;
import com.example.fishingmanagementbackend.model.FishStocking;
import com.example.fishingmanagementbackend.service.FishStockingService;

@RestController
@RequestMapping("/api/fish-stocking")
public class FishStockingController {

    @Autowired
    private FishStockingService fishStockingService;
    
    @GetMapping
    public ResponseEntity<List<FishStockingResponseDTO>> getAllFishStockings() {
        List<FishStockingResponseDTO> stockingsDTO = fishStockingService.getAllFishStockings();
        return ResponseEntity.ok(stockingsDTO);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('KEEPER')")
    public ResponseEntity<FishStockingResponseDTO> makeFishStocking(@RequestBody FishStockingRequestDTO fishStockingRequest) {
        FishStocking fishStocking = fishStockingService.makeStocking(fishStockingRequest);
        FishStockingResponseDTO fishStockingResponse = new FishStockingResponseDTO(fishStocking);
        return ResponseEntity.ok(fishStockingResponse);
    }
}
