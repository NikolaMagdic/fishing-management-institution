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
import com.example.fishingmanagementbackend.dto.YearlyCatchDTO;
import com.example.fishingmanagementbackend.enumerations.CatchItemStatus;
import com.example.fishingmanagementbackend.enumerations.FishCategory;
import com.example.fishingmanagementbackend.exceptions.ForbiddenException;
import com.example.fishingmanagementbackend.model.Catch;
import com.example.fishingmanagementbackend.model.CatchItem;
import com.example.fishingmanagementbackend.model.FishSpecies;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.FishingArea;
import com.example.fishingmanagementbackend.model.Keeper;
import com.example.fishingmanagementbackend.repository.CatchItemRepository;
import com.example.fishingmanagementbackend.repository.CatchRepository;
import com.example.fishingmanagementbackend.repository.FishSpeciesRepository;
import com.example.fishingmanagementbackend.repository.FishermanRepository;
import com.example.fishingmanagementbackend.repository.FishingAreaRepository;
import com.example.fishingmanagementbackend.repository.KeeperRepository;
import com.example.fishingmanagementbackend.repository.UserRepository;

@Service
public class CatchService {

    @Autowired
    private CatchRepository catchRepository;
    
    @Autowired
    private CatchItemRepository catchItemRepository;
    
    @Autowired
    private FishSpeciesRepository fishSpeciesRepository;
    
    @Autowired
    private FishermanRepository fishermanRepository;
    
    @Autowired
    private FishingAreaRepository fishingAreaRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
    private KeeperRepository keeperRepository;
    
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
    
    /** Vraca sve ulove ribolovca u prosledjenoj godini*/
    public List<CatchResponseDTO> getCatchesOfFishermanForYear(Long fishermanId, int year) {
        List<Catch> catches = catchRepository.findAllCatchesByFishermanForYear(fishermanId, year);
        
        List<CatchResponseDTO> catchesDTO = new ArrayList<>();
        for (Catch c : catches) {
            CatchResponseDTO catchDTO = new CatchResponseDTO(c);
            catchesDTO.add(catchDTO);
        }
        
        return catchesDTO;
    }
    
    /** Vraca evidenciju ulova za trazenog ribolovca u trazenoj godini grupisanu po vrsti ribe*/
    public List<YearlyCatchDTO> getYearCatchesOfFisherman(Long fishermanId, int year) {
        List<Object[]> catchItems = catchItemRepository.findAllCatchItemsByFishermanInYear(fishermanId, year);
        
        List<YearlyCatchDTO> catchesDTO = new ArrayList<>();
        for (Object[] c : catchItems) {
            YearlyCatchDTO yearlyCatch = new YearlyCatchDTO();
            yearlyCatch.setYearQuantity(Integer.parseInt(c[0].toString()));
            yearlyCatch.setYearWeight(Double.parseDouble(c[1].toString()));
            FishSpecies fishSpecies = fishSpeciesRepository.getReferenceById(Long.parseLong(c[2].toString()));
            FishingArea fishingArea = fishingAreaRepository.getReferenceById(Long.parseLong(c[3].toString()));
            yearlyCatch.setFishSpeciesName(fishSpecies.getName());
            yearlyCatch.setFishingAreaName(fishingArea.getName());
            catchesDTO.add(yearlyCatch);
        }
        
        return catchesDTO;
    }
    
    /** Vraca ukupnu evidenciju ulova na ribolovnoj vodi u trazenoj godini grupisanu po vrsti ribe*/
    public List<YearlyCatchDTO> getAllCatchesInFishingAreaForYear(Long areaId, int year) {
        List<Object[]> catchItems = catchItemRepository.findAllCatchItemsInFishingAreaForYear(areaId, year);
        
        List<YearlyCatchDTO> catchesDTO = new ArrayList<>();
        for(Object[] c : catchItems) {
            YearlyCatchDTO yearlyCatch = new YearlyCatchDTO();
            yearlyCatch.setYearQuantity(Integer.parseInt(c[0].toString()));
            yearlyCatch.setYearWeight(Double.parseDouble(c[1].toString()));
            FishSpecies fishSpecies = fishSpeciesRepository.getReferenceById(Long.parseLong(c[2].toString()));
            FishingArea fishingArea = fishingAreaRepository.getReferenceById(areaId);
            yearlyCatch.setFishSpeciesName(fishSpecies.getName());
            yearlyCatch.setFishingAreaName(fishingArea.getName());
            catchesDTO.add(yearlyCatch);
        }
        
        return catchesDTO;
    }
    
    /**Vraca ukupnu evidenciju ulova na ribolovnoj vodi u trazenoj godini, ali samo za plemenite vrste riba*/
    public List<YearlyCatchDTO> getAllCatchesOfNobleFishSpeciesInFishingAreaForYear(Long areaId, int year) {
        List<Object[]> catchItems = catchItemRepository.findAllCatchItemsInFishingAreaForYear(areaId, year);
        
        List<YearlyCatchDTO> catchesDTO = new ArrayList<>();
        for(Object[] c : catchItems) {
            FishSpecies fishSpecies = fishSpeciesRepository.getReferenceById(Long.parseLong(c[2].toString()));
            if(!fishSpecies.getCategory().equals(FishCategory.NOBLE))
                continue;
            YearlyCatchDTO yearlyCatch = new YearlyCatchDTO();
            yearlyCatch.setYearQuantity(Integer.parseInt(c[0].toString()));
            yearlyCatch.setYearWeight(Double.parseDouble(c[1].toString()));
            
            FishingArea fishingArea = fishingAreaRepository.getReferenceById(areaId);
            yearlyCatch.setFishSpeciesName(fishSpecies.getName());
            yearlyCatch.setFishingAreaName(fishingArea.getName());
            catchesDTO.add(yearlyCatch);
        }
        
        return catchesDTO;
    }
    
    public CatchDTO createCatch(CatchDTO dailyCatchDTO, Principal principal) {

        Catch dailyCatch = new Catch();
        dailyCatch.setDate(dailyCatchDTO.getDate());
        Set<CatchItem> catches = changeCatchItemsFromDTO(dailyCatchDTO.getCatchItems(), dailyCatch);
        dailyCatch.setCatchItems(catches);
        
        FishingArea area = fishingAreaRepository.getReferenceById(dailyCatchDTO.getFishingAreaId());
        dailyCatch.setFishingArea(area);

        Long fishermanId = userRepository.findByUsername(principal.getName()).getId();
        Fisherman fisherman = fishermanRepository.getReferenceById(fishermanId);
        
        dailyCatch.setFisherman(fisherman);

        dailyCatch = this.catchRepository.save(dailyCatch);

        return new CatchDTO(dailyCatch);
    }
    
    public CatchDTO updateCatch(CatchDTO dailyCatchDTO, Long id) {
        Catch dailyCatch = catchRepository.getReferenceById(id);
        dailyCatch.setCatchItems(changeCatchItemsFromDTO(dailyCatchDTO.getCatchItems(), dailyCatch));
        
        Catch updatedDailyCatch = catchRepository.save(dailyCatch);
        return new CatchDTO(updatedDailyCatch);
    }
    
    /**Metoda kojom ribocuvar potvrdjuje evidentirani ulov*/
    public boolean processCatchItem(Long catchItemId, CatchItemStatus status, Principal principal) throws ForbiddenException {
        Catch dailyCatch = catchRepository.findByCatchItemId(catchItemId);
        
        /* Omogucavamo samo ribocuvarima koji su zaduzeni za ribolovnu vodu na kojoj je ostvaren ulov
         da ga potvrde */
        Long keeperId = userRepository.findByUsername(principal.getName()).getId();
        Keeper keeper = keeperRepository.getReferenceById(keeperId);
        FishingArea fishingArea = dailyCatch.getFishingArea();
        if(!keeper.getFishingAreas().contains(fishingArea)) {
            throw new ForbiddenException("Ne možete potvrđivati ulov na ribolovnoj vodi za koju niste zaduženi.");
        }
        
        CatchItem item = new CatchItem();
        for (CatchItem ci : dailyCatch.getCatchItems()) {
            if(ci.getId().equals(catchItemId)) {
                item = ci;
                break;
            }
        }
        item.setStatus(status);
        item.setKeeper(keeper);
        catchRepository.save(dailyCatch);
        if(item.getId() != null) {
            return true;
        }
        return false;
    }
    
    public Set<CatchItem> changeCatchItemsFromDTO(Set<CatchItemDTO> catchDTOs, Catch dailyCatch) {
        Set<CatchItem> catches = new HashSet<>();
        for (CatchItemDTO cDTO : catchDTOs) {
            CatchItem c = new CatchItem(cDTO.getQuantity(), cDTO.getWeight(), CatchItemStatus.NOT_VERIFIED);
            FishSpecies fish = fishSpeciesRepository.getReferenceById(cDTO.getFishId());
            c.setFish(fish);
            c.setDailyCatch(dailyCatch);
            catches.add(c);
        }
        return catches;
    }
}
