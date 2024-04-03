package com.example.fishingmanagementbackend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fishingmanagementbackend.dto.CatchDTO;
import com.example.fishingmanagementbackend.dto.CatchResponseDTO;
import com.example.fishingmanagementbackend.dto.YearlyCatchDTO;
import com.example.fishingmanagementbackend.enumerations.CatchItemStatus;
import com.example.fishingmanagementbackend.exceptions.ForbiddenException;
import com.example.fishingmanagementbackend.service.CatchService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/catch")
public class CatchController {

    @Autowired
    private CatchService catchService;
    
    @GetMapping("/{id}")
    public ResponseEntity<CatchDTO> getCatchById(@PathVariable("id") Long id) {
        CatchDTO catchDTO;
        try {
            catchDTO = catchService.getCatchById(id);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(catchDTO);
    }
    
    @GetMapping("/fisherman/{id}")
    public ResponseEntity<List<CatchResponseDTO>> getAllCatchesByFisherman(@PathVariable("id") Long fishermanId) {
        List<CatchResponseDTO> catchesDTO = catchService.getCatchesOfFisherman(fishermanId);
        return ResponseEntity.ok(catchesDTO);
    }
    
    @GetMapping("/fisherman/{id}/for-year")
    public ResponseEntity<List<CatchResponseDTO>> getAllCatchesOfFishermanForYear(@PathVariable("id") Long fishermanId, @RequestParam int year) {
        List<CatchResponseDTO> catchesDTO = catchService.getCatchesOfFishermanForYear(fishermanId, year);
        return ResponseEntity.ok(catchesDTO);
    }
    
    @GetMapping("/fisherman/{id}/year")
    public ResponseEntity<List<YearlyCatchDTO>> getAllCatchesOfFishermanInYear(@PathVariable("id") Long fishermanId, @RequestParam int year) {
        List<YearlyCatchDTO> yearlyCatches = catchService.getYearCatchesOfFisherman(fishermanId, year);
        return ResponseEntity.ok(yearlyCatches);
    }
    
    @GetMapping("/fishing-area/{id}/year")
    public ResponseEntity<List<YearlyCatchDTO>> getAllCatchesInFishingAreaForYear(@PathVariable("id") Long areaId, @RequestParam int year) {
        List<YearlyCatchDTO> yearlyCatches = catchService.getAllCatchesInFishingAreaForYear(areaId, year);
        return ResponseEntity.ok(yearlyCatches);
    }
    
    @GetMapping("/noble/fishing-area/{id}/year")
    public ResponseEntity<List<YearlyCatchDTO>> getAllCatchesOfNobleFishSpeciesInFishingAreaForYear(@PathVariable("id") Long areaId, @RequestParam int year) {
        List<YearlyCatchDTO> yearlyCatches = catchService.getAllCatchesOfNobleFishSpeciesInFishingAreaForYear(areaId, year);
        return ResponseEntity.ok(yearlyCatches);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('FISHERMAN')")
    public ResponseEntity<CatchDTO> createCatch(@RequestBody CatchDTO catchDTO, Principal principal) {
        CatchDTO dailyCatch = catchService.createCatch(catchDTO, principal);
        return ResponseEntity.status(201).body(dailyCatch);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CatchDTO> updateCatch(@RequestBody CatchDTO catchDTO, @PathVariable("id") Long id) {
        CatchDTO updatedCatchDTO;
        try {
            updatedCatchDTO = catchService.updateCatch(catchDTO, id);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCatchDTO);
    }
    
    @PatchMapping("/confirm/{itemId}")
    @PreAuthorize("hasRole('KEEPER')")
    public ResponseEntity<Boolean> confirmCatchItem(@PathVariable("itemId") Long id, Principal principal) {
        Boolean success;
        try {
            success = catchService.processCatchItem(id, CatchItemStatus.CONFIRMED, principal);
        } catch (ForbiddenException fex) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(success);
    }
    
    @PatchMapping("/reject/{itemId}")
    @PreAuthorize("hasRole('KEEPER')")
    public ResponseEntity<Boolean> rejectCatchItem(@PathVariable("itemId") Long id, Principal principal) {
        Boolean success;
        try {
            success = catchService.processCatchItem(id, CatchItemStatus.CONFISCATED, principal);
        } catch (ForbiddenException fex) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(success);
    }
}
