package com.example.fishingmanagementbackend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.CatchDTO;
import com.example.fishingmanagementbackend.dto.CatchItemDTO;
import com.example.fishingmanagementbackend.model.Catch;
import com.example.fishingmanagementbackend.model.CatchItem;
import com.example.fishingmanagementbackend.model.FishSpecies;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.repository.CatchRepository;
import com.example.fishingmanagementbackend.repository.FishSpeciesRepository;
import com.example.fishingmanagementbackend.repository.FishermanRepository;

@Service
public class CatchService {

    @Autowired
    private CatchRepository catchRepository;
    
    @Autowired
    private FishSpeciesRepository fishSpeciesRepository;
    
    @Autowired
    private FishermanRepository fishermanRepository;
    
    public CatchDTO getCatchById(Long id) {
        Catch dailyCatch = catchRepository.getReferenceById(id);
        return new CatchDTO(dailyCatch);
    }
    
    public List<CatchDTO> getCatchesOfFisherman(Long fishermanId) {
        Fisherman fisherman = fishermanRepository.getReferenceById(fishermanId);
        List<Catch> catches = catchRepository.findByFisherman(fisherman);
        List<CatchDTO> catchesDTO = new ArrayList<>();
        for (Catch c : catches) {
            CatchDTO catchDTO = new CatchDTO(c);
            catchesDTO.add(catchDTO);
        }
        
        return catchesDTO;
        
    }
    
    public CatchDTO createCatch(CatchDTO dailyCatchDTO) {

        Catch dailyCatch = new Catch();
        dailyCatch.setTime(dailyCatchDTO.getDate());
        Set<CatchItem> catches = changeCatchItemsToDTO(dailyCatchDTO.getCatchItems(), dailyCatch);
        dailyCatch.setCatchItems(catches);
        // TODO: Dodati ulogovanog ribolovca
        //dailyCatch.setFisherman();

        dailyCatch = this.catchRepository.save(dailyCatch);

        return new CatchDTO(dailyCatch);
    }
    
    public CatchDTO updateCatch(CatchDTO dailyCatchDTO, Long id) {
        Catch dailyCatch = catchRepository.getReferenceById(id);
        dailyCatch.setCatchItems(changeCatchItemsToDTO(dailyCatchDTO.getCatchItems(), dailyCatch));
        
        Catch updatedDailyCatch = catchRepository.save(dailyCatch);
        return new CatchDTO(updatedDailyCatch);
    }
    
    public Set<CatchItem> changeCatchItemsToDTO(Set<CatchItemDTO> catchDTOs, Catch dailyCatch) {
        Set<CatchItem> catches = new HashSet<>();
        for (CatchItemDTO cDTO : catchDTOs) {
            CatchItem c = new CatchItem(cDTO.getQuantity(), cDTO.getWeight());
            FishSpecies fish = fishSpeciesRepository.getReferenceById(cDTO.getFishId());
            c.setFish(fish);
            c.setDailyCatch(dailyCatch);
            catches.add(c);
        }
        return catches;
    }
}
