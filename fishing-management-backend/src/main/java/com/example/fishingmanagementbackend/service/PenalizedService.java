package com.example.fishingmanagementbackend.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.PenalizedDTO;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.FishingArea;
import com.example.fishingmanagementbackend.model.Keeper;
import com.example.fishingmanagementbackend.model.Keeping;
import com.example.fishingmanagementbackend.model.Penalized;
import com.example.fishingmanagementbackend.model.Penalty;
import com.example.fishingmanagementbackend.repository.FishermanRepository;
import com.example.fishingmanagementbackend.repository.FishingAreaRepository;
import com.example.fishingmanagementbackend.repository.KeeperRepository;
import com.example.fishingmanagementbackend.repository.PenalizedRepository;
import com.example.fishingmanagementbackend.repository.PenaltyRepository;
import com.example.fishingmanagementbackend.repository.UserRepository;

@Service
public class PenalizedService {

    @Autowired
    private PenalizedRepository penalizedRepository;
    
    @Autowired
    private PenaltyRepository penaltyRepository;
    
    @Autowired
    private FishermanRepository fishermanRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private KeeperRepository keeperRepository;
    
    @Autowired
    private FishingAreaRepository fishingAreaRepository;
    
    public List<PenalizedDTO> getAllPenaltiesOfFisherman(Long fishermanId) {
        List<Penalized> penalizations = penalizedRepository.findAllByFisherman(fishermanId);
        List<PenalizedDTO> penalizationsDTO = new ArrayList<>();
        for(Penalized p : penalizations) {
            PenalizedDTO penalizationDTO = new PenalizedDTO(p);
            penalizationsDTO.add(penalizationDTO);
        }
        return penalizationsDTO;
    }
    
    public PenalizedDTO imposeAPenalty(PenalizedDTO penalizedDTO, Principal principal) {
        Penalty penalty = penaltyRepository.getReferenceById(penalizedDTO.getPenaltyId());
        Fisherman fisherman = fishermanRepository.getReferenceById(penalizedDTO.getFishermanId());
        Long keeperId = userRepository.findByUsername(principal.getName()).getId();
        Keeper keeper = keeperRepository.getReferenceById(keeperId);
        FishingArea area = fishingAreaRepository.getReferenceById(penalizedDTO.getAreaId());
        
        Penalized penalized = new Penalized(penalizedDTO.getDate(), penalizedDTO.getReport(), fisherman, penalty);
        Keeping keeping = new Keeping();
        keeping.setKeeper(keeper);
        keeping.setArea(area);
        penalized.setKeeping(keeping);
        penalizedRepository.save(penalized);
        
        return new PenalizedDTO(penalized);
    }
    
}
