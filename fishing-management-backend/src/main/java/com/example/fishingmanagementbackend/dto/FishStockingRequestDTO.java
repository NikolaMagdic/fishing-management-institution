package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

public class FishStockingRequestDTO {

    private Long id;
    
    private LocalDate date;
    
    private Integer totalWeight;
    
    private Integer amount;
    
    private Long fishingAreaId;
    
    private Long fishSpeciesId;
    
    public FishStockingRequestDTO() {}
    
    public FishStockingRequestDTO(Long id, LocalDate date, Integer totalWeight, Integer amount, Long fishingAreaId, Long fishSpeciesId) {
        this.id = id;
        this.date = date;
        this.totalWeight = totalWeight;
        this.amount = amount;
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
