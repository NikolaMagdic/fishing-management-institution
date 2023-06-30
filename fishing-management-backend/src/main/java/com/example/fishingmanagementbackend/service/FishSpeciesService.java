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
    
    /**@return Vrsta ribe sa prosledjenim id*/
    public FishSpeciesDTO getFishSpecies(Long id) {
        FishSpecies fishSpecies = fishSpeciesRepository.getReferenceById(id);
        return  new FishSpeciesDTO(fishSpecies);
    }
    
    public FishSpecies createFishSpecies(FishSpeciesDTO fishSpeciesDTO) {
        FishSpecies fishSpecies = new FishSpecies(fishSpeciesDTO.getName(), fishSpeciesDTO.getLatinName(), fishSpeciesDTO.getCategory(), fishSpeciesDTO.getMinSize(), fishSpeciesDTO.getMaxQuantity(), fishSpeciesDTO.getMaxWeight(), fishSpeciesDTO.getFishingBanStart(), fishSpeciesDTO.getFishingBanEnd(), fishSpeciesDTO.isPermanentFishingBan());
        return fishSpeciesRepository.save(fishSpecies);
    }
    
    public FishSpecies updateFishSpecies(Long id, FishSpeciesDTO fishSpeciesDTO) {
        FishSpecies fishSpecies = fishSpeciesRepository.getReferenceById(id);
        
        fishSpecies.setName(fishSpeciesDTO.getName());
        fishSpecies.setLatinName(fishSpeciesDTO.getLatinName());
        fishSpecies.setCategory(fishSpeciesDTO.getCategory());
        fishSpecies.setMinSize(fishSpeciesDTO.getMinSize());
        fishSpecies.setMaxQuantity(fishSpeciesDTO.getMaxQuantity());
        fishSpecies.setMaxWeight(fishSpeciesDTO.getMaxWeight());
        fishSpecies.setFishingBanStart(fishSpeciesDTO.getFishingBanStart());
        fishSpecies.setFishingBanEnd(fishSpeciesDTO.getFishingBanEnd());
        fishSpecies.setPermanentFishingBan(fishSpeciesDTO.isPermanentFishingBan());
    
        return fishSpeciesRepository.save(fishSpecies);
    }
}
