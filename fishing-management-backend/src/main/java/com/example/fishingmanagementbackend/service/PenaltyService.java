package com.example.fishingmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.PenaltyDTO;
import com.example.fishingmanagementbackend.model.Penalty;
import com.example.fishingmanagementbackend.repository.PenaltyRepository;

@Service
public class PenaltyService {

    @Autowired
    private PenaltyRepository penaltyRepository;
    
    public PenaltyDTO getPenaltyById(Long id) {
        Penalty penalty = this.penaltyRepository.getReferenceById(id);
        return new PenaltyDTO(penalty);
    }
    
    public List<PenaltyDTO> getAllPenalties() {
        List<Penalty> penalties = penaltyRepository.findAll();
        List<PenaltyDTO> penaltiesDTO = new ArrayList<>();
        for(Penalty p : penalties) {
            PenaltyDTO penaltyDTO = new PenaltyDTO(p);
            penaltiesDTO.add(penaltyDTO);
        }
        return penaltiesDTO;
    }
    
    public PenaltyDTO addPenalty(PenaltyDTO penaltyDTO) {
        Penalty penalty = new Penalty(penaltyDTO.getName(), penaltyDTO.getDescription(), penaltyDTO.getFine());
        penalty = this.penaltyRepository.save(penalty);
        return new PenaltyDTO(penalty);
    }
    
    public PenaltyDTO updatePenalty(Long id, PenaltyDTO penaltyDTO) {
        Penalty penalty = penaltyRepository.getReferenceById(id);
        
        penalty.setName(penaltyDTO.getName());
        penalty.setDescription(penaltyDTO.getDescription());
        penalty.setFine(penaltyDTO.getFine());
        
        penalty = penaltyRepository.save(penalty);
        
        return new PenaltyDTO(penalty);
    }
    
}
