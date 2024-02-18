package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

import com.example.fishingmanagementbackend.model.FishStocking;

public class FishStockingResponseDTO {

    private Long id;
    
    private LocalDate date;
    
    private Integer totalWeight;
    
    private Integer amount;
    
    private String fishingAreaName;
    
    private String fishSpeciesName;
    
    public FishStockingResponseDTO() {}
    
    public FishStockingResponseDTO(FishStocking fishStocking) {
        this.id = fishStocking.getId();
        this.date = fishStocking.getDate();
        this.totalWeight = fishStocking.getTotalWeight();
        this.amount = fishStocking.getAmount();
        this.fishingAreaName = fishStocking.getFishingArea().getName();
        this.fishSpeciesName = fishStocking.getFishSpecies().getName();
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
