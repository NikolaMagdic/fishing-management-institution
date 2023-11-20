package com.example.fishingmanagementbackend.dto;

import java.time.Year;

import com.example.fishingmanagementbackend.enumerations.LicenseStatus;
import com.example.fishingmanagementbackend.enumerations.LicenseType;
import com.example.fishingmanagementbackend.model.YearlyLicense;

public class YearlyLicenseDTO {

    private Long id;
    
    private LicenseType type;
    
    private Year year;
    
    private LicenseStatus status;
    
    private Long fishermanId;
    
    public YearlyLicenseDTO() {}
    
    public YearlyLicenseDTO(YearlyLicense license) {
        this.id = license.getLicenseId();
        this.type = license.getType();
        this.year = license.getYear();
        this.status = license.getStatus();
        this.fishermanId = license.getFisherman() != null ? license.getFisherman().getId() : license.getProfessionalFisherman().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LicenseType getType() {
        return type;
    }

    public void setType(LicenseType type) {
        this.type = type;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public LicenseStatus getStatus() {
        return status;
    }

    public void setStatus(LicenseStatus status) {
        this.status = status;
    }

    public Long getFishermanId() {
        return fishermanId;
    }

    public void setFishermanId(Long fishermanId) {
        this.fishermanId = fishermanId;
    }
    
    
}
