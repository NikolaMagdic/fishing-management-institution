package com.example.fishingmanagementbackend.model;

import java.time.LocalDate;

import org.hibernate.annotations.Check;

import com.example.fishingmanagementbackend.enumerations.LicenseStatus;
import com.example.fishingmanagementbackend.enumerations.LicenseType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Check(constraints = "(fisherman_id IS NOT NULL AND professional_fisherman_id IS NULL) OR (fisherman_id IS NULL AND professional_fisherman_id IS NULL)")
public class License {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long licenseId;
    
    @Column
    @Enumerated(EnumType.STRING)
    private LicenseType type;
    
    @Column
    private LocalDate date;
    
    @Column
    private LocalDate endDate;
    
    @Column
    @Enumerated(EnumType.STRING)
    private LicenseStatus status;
    
    @ManyToOne
    @JoinColumn(name = "fisherman_id", referencedColumnName = "id")
    private RecreationalFisherman fisherman;
    
    
    public License() {}
    
    public License(LicenseType type, LocalDate date, LocalDate endDate) {
        this.type = type;
        this.date = date;
        this.endDate = endDate;
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

    public RecreationalFisherman getFisherman() {
        return fisherman;
    }

    public void setFisherman(RecreationalFisherman fisherman) {
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
                + ", status=" + status + ", fisherman=" + fisherman + "]";
    }

    
    
}
