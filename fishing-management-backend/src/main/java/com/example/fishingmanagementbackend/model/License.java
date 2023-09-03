package com.example.fishingmanagementbackend.model;

import java.time.LocalDate;
import java.time.Year;

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
    
    // TODO: Videti da li dodati i visednevne dozvole
    @Column
    private LicenseType type;
    
    @Column
    private LocalDate day;
    
    @Column
    private Year year;
    
    @Column
    private boolean confirmed;
    
    @ManyToOne
    @JoinColumn(name = "fisherman_id", referencedColumnName = "id")
    private Fisherman fisherman;
    
    public License() {}
    
    public License(LicenseType type, LocalDate day, Year year) {
        this.type = type;
        this.day = day;
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

    public LocalDate getDay() {
        return day;
    }

    public void setDate(LocalDate day) {
        this.day = day;
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
    
    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "License [licenseid=" + licenseId + ", type=" + type + ", day=" + day + ", year=" + year + ", fisherman=" + fisherman
                + ", confirmed=" + confirmed + "]";
    }
    
    
}
