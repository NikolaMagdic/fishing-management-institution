package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;
import java.time.Year;

import com.example.fishingmanagementbackend.enumerations.LicenseType;
import com.example.fishingmanagementbackend.model.License;

public class LicenseDTO {

    private Long id;
    
    private LicenseType type;
    
    private LocalDate day;
    
    private Year year;
    
    private boolean confirmed;
    
    private Long fishermanId;
    
    public LicenseDTO() {}
    
    public LicenseDTO(License license) {
        this.id = license.getLicenseId();
        this.type = license.getType();
        this.day = license.getDay();
        this.year = license.getYear();
        this.confirmed = license.isConfirmed();
        this.fishermanId = license.getFisherman().getId();
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

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
    
    public Long getFishermanId() {
        return fishermanId;
    }

    public void setFishermanId(Long fishermanId) {
        this.fishermanId = fishermanId;
    }

    @Override
    public String toString() {
        return "LicenseDTO [id=" + id + ", type=" + type + ", day=" + day + ", year=" + year + ", confirmed="
                + confirmed + "]";
    }
    
    
}
