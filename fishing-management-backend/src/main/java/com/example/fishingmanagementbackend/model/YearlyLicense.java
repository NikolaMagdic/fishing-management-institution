package com.example.fishingmanagementbackend.model;

import java.time.Year;

import com.example.fishingmanagementbackend.enumerations.LicenseType;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("YearlyLicense")
public class YearlyLicense extends License {

    @Column
    private Year year;
    
    @ManyToOne
    private ProfessionalFisherman professionalFisherman;
    
    public YearlyLicense() {}
    
    public YearlyLicense(LicenseType type, Year year) {
        super(type, null, null);
        this.year = year;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public ProfessionalFisherman getProfessionalFisherman() {
        return professionalFisherman;
    }

    public void setProfessionalFisherman(ProfessionalFisherman professionalFisherman) {
        this.professionalFisherman = professionalFisherman;
    }

    @Override
    public String toString() {
        return "YearlyLicense [year=" + year + ", fisherman=" + professionalFisherman + ", LicenseId=" + getLicenseId()
                + ", Type=" + getType() + ", recreational Fisherman=" + getFisherman() + ", Status=" + getStatus()
                + "]";
    }


    
    
}
