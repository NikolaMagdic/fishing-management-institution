package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

import com.example.fishingmanagementbackend.model.FishPopulationModification;

public class FishPopulationModificationResponseDTO {

    private Long id;
    
    private LocalDate date;
    
    private Integer totalWeight;
    
    private Integer amount;
    
    private String fishingAreaName;
    
    private String fishSpeciesName;
    
    private String modificationType;
    
    public FishPopulationModificationResponseDTO() {}
    
    public FishPopulationModificationResponseDTO(FishPopulationModification fishPopulationModification) {
        this.id = fishPopulationModification.getId();
        this.date = fishPopulationModification.getDate();
        this.totalWeight = fishPopulationModification.getTotalWeight();
        this.amount = fishPopulationModification.getAmount();
        this.modificationType = fishPopulationModification.getModificationType().toString();
        this.fishingAreaName = fishPopulationModification.getFishingArea().getName();
        this.fishSpeciesName = fishPopulationModification.getFishSpecies().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getModificationType() {
        return modificationType;
    }

    public void setModificationType(String modificationType) {
        this.modificationType = modificationType;
    }

    public String getFishingAreaName() {
        return fishingAreaName;
    }

    public void setFishingAreaName(String fishingAreaName) {
        this.fishingAreaName = fishingAreaName;
    }

    public String getFishSpeciesName() {
        return fishSpeciesName;
    }

    public void setFishSpeciesName(String fishSpeciesName) {
        this.fishSpeciesName = fishSpeciesName;
    }
    
    
}
