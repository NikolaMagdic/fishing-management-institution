package com.example.fishingmanagementbackend.dto;

import com.example.fishingmanagementbackend.enumerations.FishingSpotType;
import com.example.fishingmanagementbackend.model.FishingSpot;

public class FishingSpotDTO {

    private FishingSpotType type;
    
    private Long fishingAreaId;
    
    public FishingSpotDTO() {}
    
    public FishingSpotDTO (FishingSpot fishingSpot) {
        this.type = fishingSpot.getType();
        this.fishingAreaId = fishingSpot.getFishingArea().getId();
    }

    public FishingSpotType getType() {
        return type;
    }

    public void setType(FishingSpotType type) {
        this.type = type;
    }

    public Long getFishingAreaId() {
        return fishingAreaId;
    }

    public void setFishingAreaId(Long fishingAreaId) {
        this.fishingAreaId = fishingAreaId;
    }

    @Override
    public String toString() {
        return "FishingSpotDTO [type=" + type + ", fishingAreaId=" + fishingAreaId + "]";
    }
    
    
}
