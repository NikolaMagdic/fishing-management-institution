package com.example.fishingmanagementbackend.dto;

import com.example.fishingmanagementbackend.enumerations.FishingAreaType;
import com.example.fishingmanagementbackend.model.FishingArea;

public class FishingAreaDTO {
    
    private Long id;
    
    private String name;
    
    private FishingAreaType type;
    
    private String image;

    public FishingAreaDTO() {}
    
    public FishingAreaDTO(FishingArea fishingArea) {
        this.id = fishingArea.getId();
        this.name = fishingArea.getName();
        this.type = fishingArea.getType();
        this.image = fishingArea.getImage();
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

    public FishingAreaType getType() {
        return type;
    }

    public void setType(FishingAreaType type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
}
