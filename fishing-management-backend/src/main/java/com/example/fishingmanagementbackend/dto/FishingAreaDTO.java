package com.example.fishingmanagementbackend.dto;

import com.example.fishingmanagementbackend.enumerations.FishingAreaType;
import com.example.fishingmanagementbackend.model.FishingArea;

public class FishingAreaDTO {
    
    private String name;
    
    private FishingAreaType type;

    public FishingAreaDTO() {}
    
    public FishingAreaDTO(FishingArea fishingArea) {
        this.name = fishingArea.getName();
        this.type = fishingArea.getType();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FishingAreaType getType() {
        return type;
    }

    public void setType(FishingAreaType type) {
        this.type = type;
    }
    
    
}
