package com.example.fishingmanagementbackend.dto;

import com.example.fishingmanagementbackend.enumerations.FishermanCategory;
import com.example.fishingmanagementbackend.enumerations.LicenseType;
import com.example.fishingmanagementbackend.model.License;

public class LicenseRequestDTO {

    private Long licenseId;
    
    private String fishermanFirstName;
    
    private String fishermanLastName;
    
    private FishermanCategory category;
    
    private LicenseType type;
    
    private Long fishermanId;
    
    public LicenseRequestDTO() {}

    public LicenseRequestDTO(Long licenseId, String fishermanFirstName, String fishermanLastName, FishermanCategory category, LicenseType type,
            Long fishermanId) {
        this.licenseId = licenseId;
        this.fishermanFirstName = fishermanFirstName;
        this.fishermanLastName = fishermanLastName;
        this.category = category;
        this.type = type;
        this.fishermanId = fishermanId;
    }
    
    public LicenseRequestDTO(License license) {
        this.licenseId = license.getLicenseId();
        this.fishermanFirstName = license.getFisherman().getFirstName();
        this.fishermanLastName = license.getFisherman().getLastName();
        this.category = license.getFisherman().getCategory();
        this.type = license.getType();
        this.fishermanId = license.getFisherman().getId();
    }

    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }

    public String getFishermanFirstName() {
        return fishermanFirstName;
    }

    public void setFishermanFirstName(String fishermanFirstName) {
        this.fishermanFirstName = fishermanFirstName;
    }

    public String getFishermanLastName() {
        return fishermanLastName;
    }

    public void setFishermanLastName(String fishermanLastName) {
        this.fishermanLastName = fishermanLastName;
    }

    public FishermanCategory getCategory() {
        return category;
    }

    public void setCategory(FishermanCategory category) {
        this.category = category;
    }

    public LicenseType getType() {
        return type;
    }

    public void setType(LicenseType type) {
        this.type = type;
    }

    public Long getFishermanId() {
        return fishermanId;
    }

    public void setFishermanId(Long fishermanId) {
        this.fishermanId = fishermanId;
    }
    
    
}
