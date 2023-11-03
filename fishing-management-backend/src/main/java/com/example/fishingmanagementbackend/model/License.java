package com.example.fishingmanagementbackend.model;

import java.time.LocalDate;
import java.time.Year;

import com.example.fishingmanagementbackend.enumerations.LicenseStatus;
import com.example.fishingmanagementbackend.enumerations.LicenseType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class License {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long licenseId;
    
    @Column
    private LicenseType type;
    
    @Column
    private LocalDate date;
    
    @Column
    private LocalDate endDate;
    
    @Column
    private Year year;
    
    @Column
    private LicenseStatus status;
    
    @ManyToOne
    @JoinColumn(name = "fisherman_id", referencedColumnName = "id")
    private Fisherman fisherman;
    
    
    public License() {}
    
    public License(LicenseType type, LocalDate date, LocalDate endDate, Year year) {
        this.type = type;
        this.date = date;
        this.endDate = endDate;
        this.year = year;
    }

    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
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

    public Fisherman getFisherman() {
        return fisherman;
    }

    public void setFisherman(Fisherman fisherman) {
        this.fisherman = fisherman;
    }

    public LicenseStatus getStatus() {
        return status;
    }

    public void setStatus(LicenseStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "License [licenseId=" + licenseId + ", type=" + type + ", date=" + date + ", endDate=" + endDate
                + ", year=" + year + ", status=" + status + ", fisherman=" + fisherman + "]";
    }
    
    
}
