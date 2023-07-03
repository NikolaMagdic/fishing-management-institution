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

import com.example.fishingmanagementbackend.dto.FishermanDTO;
import com.example.fishingmanagementbackend.dto.RegistrationDTO;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.service.FishermanService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/fisherman")
public class FishermanController {
    
    @Autowired
    private FishermanService fishermanService;
    
    @GetMapping
    public ResponseEntity<List<FishermanDTO>> getAllFishermans() {
        List<FishermanDTO> allFishermans = fishermanService.getAllFishermans();
        return ResponseEntity.ok().body(allFishermans);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<FishermanDTO> getFishermanById(@PathVariable("id") Long id) {
        FishermanDTO fishermanDTO;
        try {
            fishermanDTO = fishermanService.getFishermanById(id);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok().body(fishermanDTO);
    }
    
    /**Registracija novog ribolovca*/
    @PostMapping
    public ResponseEntity<FishermanDTO> registerFisherman(@RequestBody RegistrationDTO registrationDTO) {
        
        Fisherman fisherman = fishermanService.createNewFisherman(registrationDTO);
        if(fisherman == null) {
            // Ukoliko se bude radilo sa imejlom obavezno promeniti da ne bude 409 zbog sigurnosti
            return ResponseEntity.status(409).build();
        }

        return ResponseEntity.status(201).body(new FishermanDTO(fisherman));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FishermanDTO> updateFisherman(@PathVariable("id") Long id, @RequestBody FishermanDTO fishermanDTO) {
        Fisherman fisherman;
        try {
            fisherman = fishermanService.updateFisherman(id, fishermanDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new FishermanDTO(fisherman));
    }
}
