package com.example.fishingmanagementbackend.dto;

public class CatchItemDTO {

    private int quantity;
    
    private double weight;
    
    private Long fishId;
    
    public CatchItemDTO() {}
    
    public CatchItemDTO(int quantity, double weight, Long fishId) {
        this.quantity = quantity;
        this.weight = weight;
        this.fishId = fishId;
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

    public Long getFishId() {
        return fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    @Override
    public String toString() {
        return "CatchDTO [quantity=" + quantity + ", weight=" + weight + ", fishId=" + fishId + "]";
    }
    
    
}
