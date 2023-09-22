package com.example.fishingmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.FishStockingRequestDTO;
import com.example.fishingmanagementbackend.dto.FishStockingResponseDTO;
import com.example.fishingmanagementbackend.model.FishSpecies;
import com.example.fishingmanagementbackend.model.FishStocking;
import com.example.fishingmanagementbackend.model.FishingArea;
import com.example.fishingmanagementbackend.repository.FishSpeciesRepository;
import com.example.fishingmanagementbackend.repository.FishStockingRepository;
import com.example.fishingmanagementbackend.repository.FishingAreaRepository;

@Service
public class FishStockingService {

    @Autowired
    private FishStockingRepository fishStockingRepository;
    
    @Autowired
    private FishingAreaRepository fishingAreaRepository;
    
    @Autowired
    private FishSpeciesRepository fishSpeciesRepository;
    
    public List<FishStockingResponseDTO> getAllFishStockings() {
        List<FishStocking> stockings = fishStockingRepository.findAll();
        List<FishStockingResponseDTO> stockingsDTO = new ArrayList<>();
        
        for (FishStocking fs : stockings) {
            FishStockingResponseDTO stocking = new FishStockingResponseDTO(fs);
            stockingsDTO.add(stocking);
        }
        
        return stockingsDTO;
    }
    
    public FishStocking makeStocking(FishStockingRequestDTO stockingRequestDTO) {
        
        FishStocking fishStocking = new FishStocking(stockingRequestDTO.getDate(), stockingRequestDTO.getNumber());
        
        FishingArea area = fishingAreaRepository.getReferenceById(stockingRequestDTO.getFishingAreaId());
        FishSpecies fish = fishSpeciesRepository.getReferenceById(stockingRequestDTO.getFishSpeciesId());
     
        fishStocking.setFishingArea(area);
        fishStocking.setFishSpecies(fish);
        
        return fishStockingRepository.save(fishStocking);
    }
}
