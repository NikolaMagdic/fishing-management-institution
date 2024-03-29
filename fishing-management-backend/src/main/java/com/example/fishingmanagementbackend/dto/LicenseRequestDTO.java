package com.example.fishingmanagementbackend.dto;

import com.example.fishingmanagementbackend.model.License;
import com.example.fishingmanagementbackend.model.YearlyLicense;

public class LicenseRequestDTO {

    private Long licenseId;
    
    private String fishermanFirstName;
    
    private String fishermanLastName;
    
    private String category;
    
    private String type;
    
    private Long fishermanId;
    
    public LicenseRequestDTO() {}

    public LicenseRequestDTO(Long licenseId, String fishermanFirstName, String fishermanLastName, String category, String type,
            Long fishermanId) {
        this.licenseId = licenseId;
        this.fishermanFirstName = fishermanFirstName;
        this.fishermanLastName = fishermanLastName;
        this.category = category;
        this.type = type;
        this.fishermanId = fishermanId;
    }
    
    public LicenseRequestDTO(License license) {
        System.out.println(license);
        this.licenseId = license.getLicenseId();
        this.type = license.getType().toString();
        if(license.getFisherman() != null) {
            this.category = license.getFisherman().getCategory().toString();
            this.fishermanFirstName = license.getFisherman().getFirstName();
            this.fishermanLastName = license.getFisherman().getLastName();
            this.fishermanId = license.getFisherman().getId();
        } else {
            YearlyLicense yearlyLicense = (YearlyLicense) license;
            this.category = yearlyLicense.getProfessionalFisherman().getCategory().toString();
            this.fishermanFirstName = yearlyLicense.getProfessionalFisherman().getFirstName();
            this.fishermanLastName = yearlyLicense.getProfessionalFisherman().getLastName();
            this.fishermanId = yearlyLicense.getProfessionalFisherman().getId();
        }
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getFishermanId() {
        return fishermanId;
    }

    public void setFishermanId(Long fishermanId) {
        this.fishermanId = fishermanId;
    }
    
    
}
