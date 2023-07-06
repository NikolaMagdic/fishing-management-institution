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

import com.example.fishingmanagementbackend.dto.KeeperDTO;
import com.example.fishingmanagementbackend.dto.KeeperRegistrationDTO;
import com.example.fishingmanagementbackend.model.Keeper;
import com.example.fishingmanagementbackend.service.KeeperService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/keeper")
public class KeeperController {

    @Autowired
    private KeeperService keeperService;
    
    @GetMapping()
    public ResponseEntity<List<KeeperDTO>> getAllKeepers() {
        List<KeeperDTO> allKeepers = keeperService.getAllKeepers();
        return ResponseEntity.ok(allKeepers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<KeeperDTO> getKeeperById(@PathVariable("id") Long id) {
        KeeperDTO keeperDTO;
        try {
            keeperDTO = keeperService.getKeeperById(id);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(keeperDTO);
    }
    
    @PostMapping()
    public ResponseEntity<KeeperDTO> registerKeeper(@RequestBody KeeperRegistrationDTO keeperDTO) {
        Keeper keeper = keeperService.addNewKeeper(keeperDTO);
        if(keeper == null) {
            return ResponseEntity.status(409).build();
        }
        
        return ResponseEntity.status(201).body(new KeeperDTO(keeper));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<KeeperDTO> updateKeeper(@PathVariable("id") Long id, @RequestBody KeeperDTO keeperDTO) {
        Keeper keeper;
        try {
            keeper = keeperService.updateKeeper(id, keeperDTO);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(new KeeperDTO(keeper));
    }
}
