package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

public class FishStockingRequestDTO {

    private Long id;
    
    private LocalDate date;
    
    private int number;
    
    private Long fishingAreaId;
    
    private Long fishSpeciesId;
    
    public FishStockingRequestDTO() {}
    
    public FishStockingRequestDTO(Long id, LocalDate date, int number, Long fishingAreaId, Long fishSpeciesId) {
        this.id = id;
        this.date = date;
        this.number = number;
        this.fishingAreaId = fishingAreaId;
        this.fishSpeciesId = fishSpeciesId;
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

    public Long getFishingAreaId() {
        return fishingAreaId;
    }

    public void setFishingAreaId(Long fishingAreaId) {
        this.fishingAreaId = fishingAreaId;
    }

    public Long getFishSpeciesId() {
        return fishSpeciesId;
    }

    public void setFishSpeciesId(Long fishSpeciesId) {
        this.fishSpeciesId = fishSpeciesId;
    }
    
    
}
