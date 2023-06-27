package com.example.fishingmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.FishingAreaDTO;
import com.example.fishingmanagementbackend.model.FishingArea;
import com.example.fishingmanagementbackend.repository.FishingAreaRepository;

@Service
public class FishingAreaService {

    @Autowired
    private FishingAreaRepository fishingAreaRepository;
    
    public List<FishingAreaDTO> getFishingAreas() {
        List<FishingArea> fishingAreas = fishingAreaRepository.findAll();
        List<FishingAreaDTO> fishingAreaDTOs = new ArrayList<>();
        for (FishingArea fishingArea : fishingAreas) {
            FishingAreaDTO fishingAreaDTO = new FishingAreaDTO(fishingArea);
            fishingAreaDTOs.add(fishingAreaDTO);
        }
        
        return fishingAreaDTOs;
    }
    
    public FishingAreaDTO getFishingArea(Long id) {
        FishingArea fishingArea = fishingAreaRepository.getReferenceById(id);
        
        return new FishingAreaDTO(fishingArea);
    }
    
    public FishingArea createFishingArea(FishingAreaDTO fishingAreaDTO) {
        
        FishingArea fishingArea = new FishingArea(fishingAreaDTO.getName(), fishingAreaDTO.getType());
        return fishingAreaRepository.save(fishingArea);

    } 
    
    public FishingArea updateFishingArea(Long id, FishingAreaDTO fishingAreaDTO) {
        FishingArea fishingArea = fishingAreaRepository.getReferenceById(id);
        
        fishingArea.setName(fishingAreaDTO.getName());
        fishingArea.setType(fishingAreaDTO.getType());
        
        return fishingAreaRepository.save(fishingArea);
    }
    
//    public FishingArea deleteFishingArea(Long id) {
//        
//    }
//    
}
