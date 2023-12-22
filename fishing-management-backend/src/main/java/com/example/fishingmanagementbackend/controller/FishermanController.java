package com.example.fishingmanagementbackend.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fishingmanagementbackend.dto.FishermanDTO;
import com.example.fishingmanagementbackend.dto.RegistrationDTO;
import com.example.fishingmanagementbackend.exceptions.ForbiddenException;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.service.FishermanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/fisherman")
public class FishermanController {
    
    @Autowired
    private FishermanService fishermanService;
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'KEEPER')")
    public ResponseEntity<List<FishermanDTO>> getAllFishermans() {
        List<FishermanDTO> allFishermans = fishermanService.getAllFishermans();
        return ResponseEntity.ok().body(allFishermans);
    }
    
    @GetMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN', 'KEEPER')")
    public ResponseEntity<Map<String, Object>> getPageOfFishermans(@RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Map<String, Object> fishermansOnPage = fishermanService.getAllFishermans(pageable);
        return ResponseEntity.ok().body(fishermansOnPage);
    }
    
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'KEEPER')")
    public ResponseEntity<Map<String, Object>> searchFishermans(@RequestParam("page") int page, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("category") int category) {
        Pageable pageable = PageRequest.of(page, 10);
        Map<String, Object> foundFishermans = fishermanService.searchFishermans(pageable, firstName, lastName, category);
        return ResponseEntity.ok().body(foundFishermans);
    }
    
    @GetMapping("/not-confirmed-catches")
    public ResponseEntity<Map<String, Object>> getAllFishermansWithNotEvidentedCatches(Principal principal, @RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Map<String, Object> fishermans = fishermanService.getAllFishermansWithNonConfirmedCatches(principal, pageable);
        return ResponseEntity.ok().body(fishermans);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FishermanDTO> getFishermanById(@PathVariable("id") Long id, Principal principal) {
        FishermanDTO fishermanDTO;
        try {
            fishermanDTO = fishermanService.getFishermanById(id, principal);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenException fex) {
            return ResponseEntity.status(403).build();
        }
        
        return ResponseEntity.ok().body(fishermanDTO);
    }
    
    /**Registracija novog ribolovca*/
    @PostMapping
    public ResponseEntity<FishermanDTO> registerFisherman(@RequestBody RegistrationDTO registrationDTO) {
        
        Fisherman fisherman = fishermanService.createNewFisherman(registrationDTO);
        if(fisherman == null) {
            return ResponseEntity.status(409).build();
        }
        
        return ResponseEntity.status(201).body(new FishermanDTO(fisherman));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('FISHERMAN')")
    public ResponseEntity<FishermanDTO> updateFisherman(@PathVariable("id") Long id, @RequestBody FishermanDTO fishermanDTO, Principal principal) {
        Fisherman fisherman;
        try {
            fisherman = fishermanService.updateFisherman(id, fishermanDTO, principal);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenException fe) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok().body(new FishermanDTO(fisherman));
    }
    
    @GetMapping("/category")
    public ResponseEntity<String> getCategoryOfFisherman(Principal principal) throws JsonProcessingException {
        String fishermanCategory = fishermanService.getCategoryOfLoggedFisherman(principal).toString();
        ObjectMapper mapper = new ObjectMapper();
        String categoryJSON = mapper.writeValueAsString(fishermanCategory);
        return ResponseEntity.ok().body(categoryJSON);
    }
}
