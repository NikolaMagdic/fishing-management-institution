package com.example.fishingmanagementbackend.dto;

import com.example.fishingmanagementbackend.enumerations.FishingSpotType;
import com.example.fishingmanagementbackend.model.FishingSpot;

public class FishingSpotDTO {

    private Long id;
    
    private FishingSpotType type;
    
    private double latitude;
    
    private double longitude;
    
    private Long fishingAreaId;
    
    public FishingSpotDTO() {}
    
    public FishingSpotDTO (FishingSpot fishingSpot) {
        this.id = fishingSpot.getId();
        this.type = fishingSpot.getType();
        this.latitude = fishingSpot.getLatitude();
        this.longitude = fishingSpot.getLongitude();
        this.fishingAreaId = fishingSpot.getFishingArea().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FishingSpotType getType() {
        return type;
    }

    public void setType(FishingSpotType type) {
        this.type = type;
    }
    
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Long getFishingAreaId() {
        return fishingAreaId;
    }

    public void setFishingAreaId(Long fishingAreaId) {
        this.fishingAreaId = fishingAreaId;
    }

    @Override
    public String toString() {
        return "FishingSpotDTO [id=" + id + ", type=" + type + ", latitude=" + latitude + ", longitude=" + longitude
                + ", fishingAreaId=" + fishingAreaId + "]";
    }
    
    
}
