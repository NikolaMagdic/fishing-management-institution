package com.example.fishingmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.FishSpeciesDTO;
import com.example.fishingmanagementbackend.model.FishSpecies;
import com.example.fishingmanagementbackend.repository.FishSpeciesRepository;

@Service
public class FishSpeciesService {

    @Autowired
    private FishSpeciesRepository fishSpeciesRepository;
    
    /**@return Sve vrste riba */
    public List<FishSpeciesDTO> getAllFishSpecies() {
        List<FishSpecies> allFishSpecies = fishSpeciesRepository.findAll();
        List<FishSpeciesDTO> fishSpeciesDTOs = new ArrayList<>();
        for(FishSpecies fishSpecies : allFishSpecies) {
            FishSpeciesDTO fishSpeciesDTO = new FishSpeciesDTO(fishSpecies);
            fishSpeciesDTOs.add(fishSpeciesDTO);
        }
        
        return fishSpeciesDTOs;
    }
    
    /** Metoda koja vraca sve vrste riba koje pripadaju nekoj ribolovnoj vodi*/
    public List<FishSpeciesDTO> getAllFishSpeciesinFishingArea(Long areaId) {
        List<FishSpecies> fishSpeciesInArea = fishSpeciesRepository.findByFishingArea(areaId);
        List<FishSpeciesDTO> fishSpeciesDTOs = new ArrayList<>();
        for(FishSpecies fishSpecies : fishSpeciesInArea) {
            FishSpeciesDTO fishSpeciesDTO = new FishSpeciesDTO(fishSpecies);
            fishSpeciesDTOs.add(fishSpeciesDTO);
        }
        return fishSpeciesDTOs;
    }
    
    /**Metoda koja vraca sve vrste riba koje ne pripadaju ribolovnoj vodi.
     * Ovo je potrebno radi prikaza svih vrsta riba koje mogu da se dodaju u ribolovnu vodu*/
    public List<FishSpeciesDTO> getAllFishSpeciesNotInFishingArea(Long areaId) {
        List<FishSpecies> fishSpeciesNotInArea = fishSpeciesRepository.findNotInFishingArea(areaId);
        List<FishSpeciesDTO> fishSpeciesDTOs = new ArrayList<>();
        for(FishSpecies fishSpecies : fishSpeciesNotInArea) {
            FishSpeciesDTO fishSpeciesDTO = new FishSpeciesDTO(fishSpecies);
            fishSpeciesDTOs.add(fishSpeciesDTO);
        }
        return fishSpeciesDTOs;
    }
    
    /**@return Sve vrste riba koje nisu alohtone odnosno koje mogu da se poribljavaju*/
    public List<FishSpeciesDTO> getAllNativeFishSpecies() {
        List<FishSpecies> nativeFishSpecies = fishSpeciesRepository.findNativeFishSpecies();
        List<FishSpeciesDTO> nativeSpeciesDTO = new ArrayList<>();
        for(FishSpecies f: nativeFishSpecies) {
            FishSpeciesDTO fishSpeciesDTO = new FishSpeciesDTO(f);
            nativeSpeciesDTO.add(fishSpeciesDTO);
        }
        return nativeSpeciesDTO;
    }
    
    /**@return Vrsta ribe sa prosledjenim id*/
    public FishSpeciesDTO getFishSpecies(Long id) {
        FishSpecies fishSpecies = fishSpeciesRepository.getReferenceById(id);
        return  new FishSpeciesDTO(fishSpecies);
    }
    
    public FishSpecies createFishSpecies(FishSpeciesDTO fishSpeciesDTO) {

        // Ukoliko je trajni lovostaj brisu se eventualno pre toga uneti podaci o lovostaju
        if(fishSpeciesDTO.isPermanentFishingBan()) {
            fishSpeciesDTO.setFishingBanStartDay(0);
            fishSpeciesDTO.setFishingBanStartMonth(0);
            fishSpeciesDTO.setFishingBanEndDay(0);
            fishSpeciesDTO.setFishingBanEndMonth(0);
        }
        FishSpecies fishSpecies = new FishSpecies(fishSpeciesDTO.getName(), 
                                                  fishSpeciesDTO.getLatinName(), 
                                                  fishSpeciesDTO.getCategory(), 
                                                  fishSpeciesDTO.getMinSize(), 
                                                  fishSpeciesDTO.getMaxQuantity(),
                                                  fishSpeciesDTO.getFishingBanStartDay(),
                                                  fishSpeciesDTO.getFishingBanStartMonth(),
                                                  fishSpeciesDTO.getFishingBanEndDay(),
                                                  fishSpeciesDTO.getFishingBanEndMonth(),
                                                  fishSpeciesDTO.isPermanentFishingBan(), 
                                                  fishSpeciesDTO.getDescription(),
                                                  fishSpeciesDTO.getImage());
        
        
        System.out.println(fishSpecies.getImage());
        return fishSpeciesRepository.save(fishSpecies);
    }
    
    public FishSpecies updateFishSpecies(Long id, FishSpeciesDTO fishSpeciesDTO) {
        FishSpecies fishSpecies = fishSpeciesRepository.getReferenceById(id);
        
        fishSpecies.setName(fishSpeciesDTO.getName());
        fishSpecies.setLatinName(fishSpeciesDTO.getLatinName());
        fishSpecies.setCategory(fishSpeciesDTO.getCategory());
        fishSpecies.setMinSize(fishSpeciesDTO.getMinSize());
        fishSpecies.setMaxQuantity(fishSpeciesDTO.getMaxQuantity());
        fishSpecies.setFishingBanStartDay(fishSpeciesDTO.getFishingBanStartDay());
        fishSpecies.setFishingBanStartMonth(fishSpeciesDTO.getFishingBanStartMonth());
        fishSpecies.setFishingBanEndDay(fishSpeciesDTO.getFishingBanEndDay());
        fishSpecies.setFishingBanEndMonth(fishSpeciesDTO.getFishingBanEndMonth());
        fishSpecies.setPermanentFishingBan(fishSpeciesDTO.isPermanentFishingBan());
        fishSpecies.setDescription(fishSpeciesDTO.getDescription());
    
        return fishSpeciesRepository.save(fishSpecies);
    }
}
