package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

import com.example.fishingmanagementbackend.model.FishStocking;

public class FishStockingResponseDTO {

    private Long id;
    
    private LocalDate date;
    
    private int number;
    
    private String fishingAreaName;
    
    private String fishSpeciesName;
    
    public FishStockingResponseDTO() {}
    
    public FishStockingResponseDTO(FishStocking fishStocking) {
        this.id = fishStocking.getId();
        this.date = fishStocking.getDate();
        this.number = fishStocking.getNumber();
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
