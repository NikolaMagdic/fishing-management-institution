package com.example.fishingmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.FishingSpotDTO;
import com.example.fishingmanagementbackend.enumerations.FishingSpotType;
import com.example.fishingmanagementbackend.model.FishingArea;
import com.example.fishingmanagementbackend.model.FishingSpot;
import com.example.fishingmanagementbackend.model.FishingSpotPK;
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
    
    public FishingSpotDTO getFishingSpotById(Long spotId, Long areaId) {
        FishingSpotPK id = new FishingSpotPK(spotId, areaId);
        FishingSpot fishingSpot = fishingSpotRepository.getReferenceById(id);
        return new FishingSpotDTO(fishingSpot);
    }
    
    public FishingSpot addFishingSpotToArea(FishingSpotDTO fishingSpotDTO) throws Exception {
        FishingSpot fishingSpot = new FishingSpot(FishingSpotType.valueOf(fishingSpotDTO.getType()), fishingSpotDTO.getLatitude(), fishingSpotDTO.getLongitude(), fishingSpotDTO.getImage());
        FishingArea fishingArea = fishingAreaRepository.getReferenceById(fishingSpotDTO.getFishingAreaId());
        fishingSpot.setFishingArea(fishingArea);
        
        return fishingSpotRepository.save(fishingSpot);
    }
    
    public FishingSpot updateFishingSpot(Long id, FishingSpotDTO fishingSpotDTO) {
        FishingSpot fishingSpot = fishingSpotRepository.getReferenceById(new FishingSpotPK(id, fishingSpotDTO.getFishingAreaId()));
        
        fishingSpot.setType(FishingSpotType.valueOf(fishingSpotDTO.getType()));
        fishingSpot.setLatitude(fishingSpotDTO.getLatitude());
        fishingSpot.setLongitude(fishingSpotDTO.getLongitude());
        
        return fishingSpotRepository.save(fishingSpot);
    }
        

}
