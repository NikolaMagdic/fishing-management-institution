package com.example.fishingmanagementbackend.dto;

import com.example.fishingmanagementbackend.enumerations.CatchItemStatus;

public class CatchItemResponseDTO {

    private Long id;
    
    private int quantity;
    
    private double weight;
        
    private CatchItemStatus status;
    
    private String fishSpeciesName;
    
    private String keeperName;
    
    public CatchItemResponseDTO() {}
    
    public CatchItemResponseDTO(Long id, int quantity, double weight, CatchItemStatus status, String fishSpeciesName) {
        this.id = id;
        this.quantity = quantity;
        this.weight = weight;
        this.status = status;
        this.fishSpeciesName = fishSpeciesName;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getFishSpeciesName() {
        return fishSpeciesName;
    }

    public void setFishSpeciesName(String fishSpeciesName) {
        this.fishSpeciesName = fishSpeciesName;
    }

    public CatchItemStatus getStatus() {
        return status;
    }

    public void setStatus(CatchItemStatus status) {
        this.status = status;
    }

    public String getKeeperName() {
        return keeperName;
    }

    public void setKeeperName(String keeperName) {
        this.keeperName = keeperName;
    }
    
  
}
