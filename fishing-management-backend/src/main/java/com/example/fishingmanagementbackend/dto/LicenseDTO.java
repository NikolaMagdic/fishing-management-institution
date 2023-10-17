package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;
import java.time.Year;

import com.example.fishingmanagementbackend.enumerations.LicenseStatus;
import com.example.fishingmanagementbackend.enumerations.LicenseType;
import com.example.fishingmanagementbackend.model.License;

public class LicenseDTO {

    private Long id;
    
    private LicenseType type;
    
    private LocalDate date;
    
    private LocalDate endDate;
    
    private Year year;
    
    private LicenseStatus status;
    
    private Long fishermanId;
    
    private Long spotId;
    
    public LicenseDTO() {}
    
    public LicenseDTO(License license) {
        this.id = license.getLicenseId();
        this.type = license.getType();
        this.date = license.getDate();
        this.endDate = license.getEndDate();
        this.year = license.getYear();
        this.status = license.getStatus();
        this.fishermanId = license.getFisherman().getId();
        this.spotId = license.getSpotId();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
    }

    @Override
    public String toString() {
        return "LicenseDTO [id=" + id + ", type=" + type + ", date=" + date + ", endDate=" + endDate + ", year=" + year
                + ", status=" + status + ", fishermanId=" + fishermanId + ", spotId=" + spotId + "]";
    }

    
}
