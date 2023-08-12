package com.example.fishingmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.FishingSpotDTO;
import com.example.fishingmanagementbackend.model.FishingArea;
import com.example.fishingmanagementbackend.model.FishingSpot;
import com.example.fishingmanagementbackend.repository.FishingAreaRepository;
import com.example.fishingmanagementbackend.repository.FishingSpotRepository;

@Service    
public class FishingSpotService {
    
    @Autowired 
    private FishingSpotRepository fishingSpotRepository;
    
    @Autowired
    private FishingAreaRepository fishingAreaRepository;

    public List<FishingSpotDTO> getAllFishingSpotsInArea(Long areaId) {
        List<FishingSpot> fishingSpots = fishingSpotRepository.findByFishingArea(areaId);
        List<FishingSpotDTO> fishingSpotDTOs = new ArrayList<>();
        for (FishingSpot fishingSpot : fishingSpots) {
            FishingSpotDTO fishingSpotDTO = new FishingSpotDTO(fishingSpot);
            fishingSpotDTOs.add(fishingSpotDTO);
        }
        return fishingSpotDTOs;
    }
    
    public FishingSpotDTO getFishingSpotById(Long id) {
        FishingSpot fishingSpot = fishingSpotRepository.getReferenceById(id);
        return new FishingSpotDTO(fishingSpot);
    }
    
    public FishingSpot addFishingSpotToArea(FishingSpotDTO fishingSpotDTO) throws Exception{
        FishingSpot fishingSpot = new FishingSpot(fishingSpotDTO.getType(), fishingSpotDTO.getLatitude(), fishingSpotDTO.getLongitude());
        FishingArea fishingArea = fishingAreaRepository.getReferenceById(fishingSpotDTO.getFishingAreaId());
        fishingSpot.setFishingArea(fishingArea);
        
        return fishingSpotRepository.save(fishingSpot);
    }
    
    public FishingSpot updateFishingSpot(Long id, FishingSpotDTO fishingSpotDTO) {
        FishingSpot fishingSpot = fishingSpotRepository.getReferenceById(id);
        
        fishingSpot.setType(fishingSpotDTO.getType());
        fishingSpot.setLatitude(fishingSpotDTO.getLatitude());
        fishingSpot.setLongitude(fishingSpotDTO.getLongitude());
        
        return fishingSpotRepository.save(fishingSpot);
    }
        

}
