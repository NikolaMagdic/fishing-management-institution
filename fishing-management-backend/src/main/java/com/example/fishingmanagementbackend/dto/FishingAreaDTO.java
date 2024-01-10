package com.example.fishingmanagementbackend.dto;

import com.example.fishingmanagementbackend.enumerations.FishingAreaType;
import com.example.fishingmanagementbackend.model.FishingArea;

public class FishingAreaDTO {
    
    private Long id;
    
    private String name;
    
    private String description;
    
    private boolean allowedFishing;
    
    private FishingAreaType type;
    
    private Long parentArea;
    
    private String image;

    public FishingAreaDTO() {}
    
    public FishingAreaDTO(FishingArea fishingArea) {
        this.id = fishingArea.getId();
        this.name = fishingArea.getName();
        this.description = fishingArea.getDescription();
        this.type = fishingArea.getType();
        this.allowedFishing = fishingArea.isAllowedFishing();
        this.parentArea = fishingArea.getParentArea() == null ? null : fishingArea.getParentArea().getId();
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
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FishingAreaType getType() {
        return type;
    }

    public void setType(FishingAreaType type) {
        this.type = type;
    }
    
    public boolean isAllowedFishing() {
        return allowedFishing;
    }

    public void setAllowedFishing(boolean allowedFishing) {
        this.allowedFishing = allowedFishing;
    }

    public Long getParentArea() {
        return parentArea;
    }

    public void setParentArea(Long parentArea) {
        this.parentArea = parentArea;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FishingAreaDTO [id=" + id + ", name=" + name + ", description=" + description + ", allowedFishing="
                + allowedFishing + ", type=" + type + ", parentArea=" + parentArea + ", image=" + image + "]";
    }

}
