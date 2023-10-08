package com.example.fishingmanagementbackend.dto;

public class YearlyCatchDTO {

    private String fishingAreaName;
    
    private String fishSpeciesName;
    
    private int yearQuantity;
    
    private double yearWeight;
    
    public YearlyCatchDTO() {}
    
    public YearlyCatchDTO(String fishingAreaName, String fishSpeciesName, int yearQuantity, double yearWeight) {
        this.fishingAreaName = fishingAreaName;
        this.fishSpeciesName = fishSpeciesName;
        this.yearQuantity = yearQuantity;
        this.yearWeight = yearWeight;
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

    public int getYearQuantity() {
        return yearQuantity;
    }

    public void setYearQuantity(int yearQuantity) {
        this.yearQuantity = yearQuantity;
    }

    public double getYearWeight() {
        return yearWeight;
    }

    public void setYearWeight(double yearWeight) {
        this.yearWeight = yearWeight;
    }
    
    
}
