package com.example.fishingmanagementbackend.dto;

import java.util.Date;

import com.example.fishingmanagementbackend.enumerations.FishCategory;
import com.example.fishingmanagementbackend.model.FishSpecies;

public class FishSpeciesDTO {
    
    private Long id;

    private String name;
    
    private String latinName;
    
    private FishCategory category;
    
    private int minSize;
    
    private int maxQuantity;
    
    private Date fishingBanStart;
    
    private Date fishingBanEnd;
    
    private boolean permanentFishingBan;
    
    private String image;
    
    public FishSpeciesDTO() {
        
    }

    public FishSpeciesDTO(FishSpecies fishSpecies) {
        this.id = fishSpecies.getId();
        this.name = fishSpecies.getName();
        this.latinName = fishSpecies.getLatinName();
        this.category = fishSpecies.getCategory();
        this.minSize = fishSpecies.getMinSize();
        this.maxQuantity = fishSpecies.getMaxQuantity();
        this.fishingBanStart = fishSpecies.getFishingBanStart();
        this.fishingBanEnd = fishSpecies.getFishingBanEnd();
        this.permanentFishingBan = fishSpecies.isPermanentFishingBan();
        this.image = fishSpecies.getImage();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FishSpeciesDTO [id=" + id + ", name=" + name + ", latinName=" + latinName + ", category=" + category
                + ", minSize=" + minSize + ", maxQuantity=" + maxQuantity + ", fishingBanStart=" + fishingBanStart
                + ", fishingBanEnd=" + fishingBanEnd + ", permanentFishingBan=" + permanentFishingBan + ", image="
                + image + "]";
    }
    
    
    
}
