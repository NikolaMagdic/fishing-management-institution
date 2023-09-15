package com.example.fishingmanagementbackend.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.CatchDTO;
import com.example.fishingmanagementbackend.dto.CatchItemDTO;
import com.example.fishingmanagementbackend.dto.CatchResponseDTO;
import com.example.fishingmanagementbackend.model.Catch;
import com.example.fishingmanagementbackend.model.CatchItem;
import com.example.fishingmanagementbackend.model.FishSpecies;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.FishingArea;
import com.example.fishingmanagementbackend.repository.CatchRepository;
import com.example.fishingmanagementbackend.repository.FishSpeciesRepository;
import com.example.fishingmanagementbackend.repository.FishermanRepository;
import com.example.fishingmanagementbackend.repository.FishingAreaRepository;
import com.example.fishingmanagementbackend.repository.UserRepository;

@Service
public class CatchService {

    @Autowired
    private CatchRepository catchRepository;
    
    @Autowired
    private FishSpeciesRepository fishSpeciesRepository;
    
    @Autowired
    private FishermanRepository fishermanRepository;
    
    @Autowired
    private FishingAreaRepository fishingAreaRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public CatchDTO getCatchById(Long id) {
        Catch dailyCatch = catchRepository.getReferenceById(id);
        return new CatchDTO(dailyCatch);
    }
    
    public List<CatchResponseDTO> getCatchesOfFisherman(Long fishermanId) {
        Fisherman fisherman = fishermanRepository.getReferenceById(fishermanId);
        List<Catch> catches = catchRepository.findByFisherman(fisherman);

        List<CatchResponseDTO> catchesDTO = new ArrayList<>();
        for (Catch c : catches) {
            CatchResponseDTO catchDTO = new CatchResponseDTO(c);
            catchesDTO.add(catchDTO);
        }
        
        return catchesDTO;
        
    }
    
    public CatchDTO createCatch(CatchDTO dailyCatchDTO, Principal principal) {

        Catch dailyCatch = new Catch();
        dailyCatch.setTime(dailyCatchDTO.getDate());
        Set<CatchItem> catches = changeCatchItemsToDTO(dailyCatchDTO.getCatchItems(), dailyCatch);
        dailyCatch.setCatchItems(catches);
        
        FishingArea area = fishingAreaRepository.getReferenceById(dailyCatchDTO.getFishingAreaId());
        dailyCatch.setFishingArea(area);

        Fisherman fisherman = userRepository.findByUsername(principal.getName()).getFisherman();
        dailyCatch.setFisherman(fisherman);

        dailyCatch = this.catchRepository.save(dailyCatch);

        return new CatchDTO(dailyCatch);
    }
    
    public CatchDTO updateCatch(CatchDTO dailyCatchDTO, Long id) {
        Catch dailyCatch = catchRepository.getReferenceById(id);
        dailyCatch.setCatchItems(changeCatchItemsToDTO(dailyCatchDTO.getCatchItems(), dailyCatch));
        
        Catch updatedDailyCatch = catchRepository.save(dailyCatch);
        return new CatchDTO(updatedDailyCatch);
    }
    
    /**Metoda kojom ribocuvar potvrdjuje evidentirani ulov*/
    public boolean confirmCatchItem(Long catchItemId) {
        Catch dailyCatch = catchRepository.findByCatchItemId(catchItemId);
        CatchItem item = new CatchItem();
        for (CatchItem ci : dailyCatch.getCatchItems()) {
            if(ci.getId().equals(catchItemId)) {
                item = ci;
                break;
            }
        }
        item.setConfirmed(true);
        catchRepository.save(dailyCatch);
        if(item.getId() != null) {
            return true;
        }
        return false;
    }
    
    public Set<CatchItem> changeCatchItemsToDTO(Set<CatchItemDTO> catchDTOs, Catch dailyCatch) {
        Set<CatchItem> catches = new HashSet<>();
        for (CatchItemDTO cDTO : catchDTOs) {
            CatchItem c = new CatchItem(cDTO.getQuantity(), cDTO.getWeight(), false);
            FishSpecies fish = fishSpeciesRepository.getReferenceById(cDTO.getFishId());
            c.setFish(fish);
            c.setDailyCatch(dailyCatch);
            catches.add(c);
        }
        return catches;
    }
}
