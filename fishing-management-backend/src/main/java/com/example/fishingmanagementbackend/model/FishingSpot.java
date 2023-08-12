package com.example.fishingmanagementbackend.model;

import com.example.fishingmanagementbackend.enumerations.FishingSpotType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FishingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private FishingSpotType type;
    
    @Column
    private double latitude;
    
    @Column
    private double longitude;
    
    // Za sad nisam uspeo da ovo ukjucim u kompozitni kljuc. Videti jos da li je neophodno
    @ManyToOne(optional = false)
    @JoinColumn(name = "fishing_area_id", referencedColumnName = "id")
    private FishingArea fishingArea;
    
    public FishingSpot() {}
    
    public FishingSpot(FishingSpotType type, double latitude, double longitude) {
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FishingSpotType getType() {
        return type;
    }

    public void setType(FishingSpotType type) {
        this.type = type;
    }
    
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public FishingArea getFishingArea() {
        return fishingArea;
    }

    public void setFishingArea(FishingArea fishingArea) {
        this.fishingArea = fishingArea;
    }

    @Override
    public String toString() {
        return "FishingSpot [id=" + id + ", type=" + type + ", latitude=" + latitude + ", longitude=" + longitude
                + ", fishingArea=" + fishingArea + "]";
    }
    
    
}
