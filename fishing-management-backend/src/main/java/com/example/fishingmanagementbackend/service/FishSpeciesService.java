package com.example.fishingmanagementbackend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.FishSpeciesDTO;
import com.example.fishingmanagementbackend.enumerations.FishCategory;
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
    
    
    /**@return Vrsta ribe sa prosledjenim id*/
    public FishSpeciesDTO getFishSpecies(Long id) {
        FishSpecies fishSpecies = fishSpeciesRepository.getReferenceById(id);
        return  new FishSpeciesDTO(fishSpecies);
    }
    
    public FishSpecies createFishSpecies(FishSpeciesDTO fishSpeciesDTO) {

        FishSpecies fishSpecies = new FishSpecies(fishSpeciesDTO.getName(), fishSpeciesDTO.getLatinName(), FishCategory.valueOf(fishSpeciesDTO.getCategory()), fishSpeciesDTO.getMinSize(), fishSpeciesDTO.getMaxQuantity(), LocalDate.of(2023, fishSpeciesDTO.getFishingBanStartMonth(), fishSpeciesDTO.getFishingBanStartDay()), LocalDate.of(2023, fishSpeciesDTO.getFishingBanEndMonth(), fishSpeciesDTO.getFishingBanEndDay()), fishSpeciesDTO.isPermanentFishingBan(), fishSpeciesDTO.getImage());
        return fishSpeciesRepository.save(fishSpecies);
    }
    
    public FishSpecies updateFishSpecies(Long id, FishSpeciesDTO fishSpeciesDTO) {
        FishSpecies fishSpecies = fishSpeciesRepository.getReferenceById(id);
        
        fishSpecies.setName(fishSpeciesDTO.getName());
        fishSpecies.setLatinName(fishSpeciesDTO.getLatinName());
        fishSpecies.setCategory(FishCategory.valueOf(fishSpeciesDTO.getCategory()));
        fishSpecies.setMinSize(fishSpeciesDTO.getMinSize());
        fishSpecies.setMaxQuantity(fishSpeciesDTO.getMaxQuantity());
        fishSpecies.setFishingBanStart(LocalDate.of(2023, fishSpeciesDTO.getFishingBanStartMonth(), fishSpeciesDTO.getFishingBanStartDay()));
        fishSpecies.setFishingBanEnd(LocalDate.of(2023, fishSpeciesDTO.getFishingBanEndMonth(), fishSpeciesDTO.getFishingBanEndDay()));
        fishSpecies.setPermanentFishingBan(fishSpeciesDTO.isPermanentFishingBan());
    
        return fishSpeciesRepository.save(fishSpecies);
    }
}
