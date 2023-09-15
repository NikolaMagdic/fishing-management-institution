package com.example.fishingmanagementbackend.dto;

public class CatchItemResponseDTO {

    private Long id;
    
    private int quantity;
    
    private double weight;
    
    private boolean confirmed;
    
    private String fishSpeciesName;
    
    public CatchItemResponseDTO() {}
    
    public CatchItemResponseDTO(Long id, int quantity, double weight, boolean confirmed, String fishSpeciesName) {
        this.id = id;
        this.quantity = quantity;
        this.weight = weight;
        this.confirmed = confirmed;
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

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getFishSpeciesName() {
        return fishSpeciesName;
    }

    public void setFishSpeciesName(String fishSpeciesName) {
        this.fishSpeciesName = fishSpeciesName;
    }
    
    
}
