package com.example.fishingmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.FishPopulationModificationRequestDTO;
import com.example.fishingmanagementbackend.dto.FishPopulationModificationResponseDTO;
import com.example.fishingmanagementbackend.model.FishSpecies;
import com.example.fishingmanagementbackend.model.FishPopulationModification;
import com.example.fishingmanagementbackend.model.FishingArea;
import com.example.fishingmanagementbackend.repository.FishSpeciesRepository;
import com.example.fishingmanagementbackend.repository.FishStockingRepository;
import com.example.fishingmanagementbackend.repository.FishingAreaRepository;

@Service
public class FishPopulationModificationService {

    @Autowired
    private FishStockingRepository fishStockingRepository;
    
    @Autowired
    private FishingAreaRepository fishingAreaRepository;
    
    @Autowired
    private FishSpeciesRepository fishSpeciesRepository;
    
    public List<FishPopulationModificationResponseDTO> getAllFishPopulationModifications() {
        List<FishPopulationModification> modifications = fishStockingRepository.findAll();
        List<FishPopulationModificationResponseDTO> modificationsDTO = new ArrayList<>();
        
        for (FishPopulationModification fpm : modifications) {
            FishPopulationModificationResponseDTO modification = new FishPopulationModificationResponseDTO(fpm);
            modificationsDTO.add(modification);
        }
        
        return modificationsDTO;
    }
    
    public FishPopulationModification makeModification(FishPopulationModificationRequestDTO modificationRequestDTO) {
        
        FishPopulationModification fishPopulationModification = new FishPopulationModification(modificationRequestDTO.getDate(), modificationRequestDTO.getTotalWeight(), modificationRequestDTO.getAmount(), modificationRequestDTO.getModificationType());
        
        FishingArea area = fishingAreaRepository.getReferenceById(modificationRequestDTO.getFishingAreaId());
        FishSpecies fish = fishSpeciesRepository.getReferenceById(modificationRequestDTO.getFishSpeciesId());
     
        fishPopulationModification.setFishingArea(area);
        fishPopulationModification.setFishSpecies(fish);
        
        return fishStockingRepository.save(fishPopulationModification);
    }
}
