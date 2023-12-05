package com.example.fishingmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.FishingAreaDTO;
import com.example.fishingmanagementbackend.model.FishSpecies;
import com.example.fishingmanagementbackend.model.FishingArea;
import com.example.fishingmanagementbackend.repository.FishSpeciesRepository;
import com.example.fishingmanagementbackend.repository.FishingAreaRepository;

@Service
public class FishingAreaService {

    @Autowired
    private FishingAreaRepository fishingAreaRepository;
    
    @Autowired
    private FishSpeciesRepository fishSpeciesRepository;
    
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
        
        FishingArea fishingArea = new FishingArea(fishingAreaDTO.getName(), fishingAreaDTO.getDescription(), fishingAreaDTO.getType(), fishingAreaDTO.getImage());
        
        // Ukoliko je u sklopu neke druge ribolovne vode
        if(fishingAreaDTO.getParentArea() != null) {
            FishingArea parentArea = fishingAreaRepository.getReferenceById(fishingAreaDTO.getParentArea());
            fishingArea.setParentArea(parentArea);
        }
        
        return fishingAreaRepository.save(fishingArea);

    } 
    
    public List<FishingAreaDTO> getAllRootFishingAreas() {
        
        List<FishingArea> rootFishingAreas = fishingAreaRepository.findAllRootFishingAreas();
        List<FishingAreaDTO> rootFishingAreasDTO = new ArrayList<>();
        
        for (FishingArea fa :  rootFishingAreas) {
            FishingAreaDTO areaDTO = new FishingAreaDTO(fa);
            rootFishingAreasDTO.add(areaDTO);
        }
        
        return rootFishingAreasDTO;
    }
    
    public List<FishingAreaDTO> getChildsFishingAreasOfFishingArea(Long fishingAreaId) {
        
        List<FishingArea> childFishingAreas = fishingAreaRepository.findContainedFishingAreas(fishingAreaId);
        List<FishingAreaDTO> areasDTO = new ArrayList<>();
        
        for (FishingArea fa : childFishingAreas) {
            FishingAreaDTO areaDTO = new FishingAreaDTO(fa);
            areasDTO.add(areaDTO);
        }
        
        return areasDTO;
    }
    
    public FishingArea updateFishingArea(Long id, FishingAreaDTO fishingAreaDTO) {
        FishingArea fishingArea = fishingAreaRepository.getReferenceById(id);
        
        fishingArea.setName(fishingAreaDTO.getName());
        fishingArea.setDescription(fishingAreaDTO.getDescription());
        fishingArea.setType(fishingAreaDTO.getType());
        fishingArea.setImage(fishingAreaDTO.getImage());
        
        return fishingAreaRepository.save(fishingArea);
    }
    
    public boolean addFishSpeciesToArea(Long areaId, Long fishSpeciesId) {
        FishingArea fishingArea = fishingAreaRepository.getReferenceById(areaId);
        FishSpecies fishSpecies = fishSpeciesRepository.getReferenceById(fishSpeciesId);
        // TODO: Ovde dodati rukovanje izuzecima
        fishingArea.getFishSpecies().add(fishSpecies);
        fishSpecies.getFishingAreas().add(fishingArea);

        fishingAreaRepository.save(fishingArea);
        fishSpeciesRepository.save(fishSpecies);
        
        return true;
    }
    
    public boolean removeFishSpeciesFromArea(Long areaId, Long fishSpeciesId) {
        FishingArea fishingArea = fishingAreaRepository.getReferenceById(areaId);
        FishSpecies fishSpecies = fishSpeciesRepository.getReferenceById(fishSpeciesId);
        // TODO: I ovde obraditi izuzetke i vratiti false ako postoje
        fishingArea.getFishSpecies().remove(fishSpecies);
        fishSpecies.getFishingAreas().remove(fishingArea);
        
        fishingAreaRepository.save(fishingArea);
        fishSpeciesRepository.save(fishSpecies);
        
        return true;
    }
    
    
    public List<FishingAreaDTO> getAllFishingAreasThisKeeperKeeps(Long keeperId) {
        List<FishingArea> fishingAreas = fishingAreaRepository.findKeptByKeeper(keeperId);
        List<FishingAreaDTO> fishingAreaDTOs = new ArrayList<>();
        for(FishingArea fishingArea: fishingAreas) {
            FishingAreaDTO areaDTO = new FishingAreaDTO(fishingArea);
            fishingAreaDTOs.add(areaDTO);
        }
        return fishingAreaDTOs;
    }
    
    public List<FishingAreaDTO> getAllFishingAreasThisKeeperNotKeeps(Long keeperId) {
        List<FishingArea> fishingAreas = fishingAreaRepository.findNotKeptByKeeper(keeperId);
        List<FishingAreaDTO> fishingAreaDTOs = new ArrayList<>();
        for(FishingArea fishingArea: fishingAreas) {
            FishingAreaDTO areaDTO = new FishingAreaDTO(fishingArea);
            fishingAreaDTOs.add(areaDTO);
        }
        return fishingAreaDTOs;
    }
    
//    public FishingArea deleteFishingArea(Long id) {
//        
//    }
//    
}
