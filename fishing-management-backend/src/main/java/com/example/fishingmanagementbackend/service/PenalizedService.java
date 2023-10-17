package com.example.fishingmanagementbackend.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.PenalizedDTO;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.Keeper;
import com.example.fishingmanagementbackend.model.Penalized;
import com.example.fishingmanagementbackend.model.Penalty;
import com.example.fishingmanagementbackend.repository.FishermanRepository;
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
        Keeper keeper = userRepository.findByUsername(principal.getName()).getKeeper();
        
        Penalized penalized = new Penalized(penalizedDTO.getDate(), penalizedDTO.getReport(), fisherman, penalty);
        penalized.setKeeper(keeper);
        penalizedRepository.save(penalized);
        
        return new PenalizedDTO(penalized);
    }
    
}
