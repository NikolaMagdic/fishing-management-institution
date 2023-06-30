package com.example.fishingmanagementbackend.dto;

import java.util.Date;

import com.example.fishingmanagementbackend.enumerations.FishCategory;
import com.example.fishingmanagementbackend.model.FishSpecies;

public class FishSpeciesDTO {

    private String name;
    
    private String latinName;
    
    private FishCategory category;
    
    private int minSize;
    
    private int maxQuantity;
    
    private int maxWeight;
    
    private Date fishingBanStart;
    
    private Date fishingBanEnd;
    
    boolean permanentFishingBan;
    
    public FishSpeciesDTO() {
        
    }

    public FishSpeciesDTO(FishSpecies fishSpecies) {
        this.name = fishSpecies.getName();
        this.latinName = fishSpecies.getLatinName();
        this.category = fishSpecies.getCategory();
        this.minSize = fishSpecies.getMinSize();
        this.maxQuantity = fishSpecies.getMaxQuantity();
        this.maxWeight = fishSpecies.getMaxWeight();
        this.fishingBanStart = fishSpecies.getFishingBanStart();
        this.fishingBanEnd = fishSpecies.getFishingBanEnd();
        this.permanentFishingBan = fishSpecies.isPermanentFishingBan();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public FishCategory getCategory() {
        return category;
    }

    public void setCategory(FishCategory category) {
        this.category = category;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Date getFishingBanStart() {
        return fishingBanStart;
    }

    public void setFishingBanStart(Date fishingBanStart) {
        this.fishingBanStart = fishingBanStart;
    }

    public Date getFishingBanEnd() {
        return fishingBanEnd;
    }

    public void setFishingBanEnd(Date fishingBanEnd) {
        this.fishingBanEnd = fishingBanEnd;
    }

    public boolean isPermanentFishingBan() {
        return permanentFishingBan;
    }

    public void setPermanentFishingBan(boolean permanentFishingBan) {
        this.permanentFishingBan = permanentFishingBan;
    }

    @Override
    public String toString() {
        return "FishSpeciesDTO [name=" + name + ", latinName=" + latinName + ", category=" + category + ", minSize="
                + minSize + ", maxQuantity=" + maxQuantity + ", maxWeight=" + maxWeight + ", fishingBanStart="
                + fishingBanStart + ", fishingBanEnd=" + fishingBanEnd + ", permanentFishingBan=" + permanentFishingBan
                + "]";
    }
    
    
    
}
