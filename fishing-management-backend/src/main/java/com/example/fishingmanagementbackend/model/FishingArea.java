package com.example.fishingmanagementbackend.model;

import com.example.fishingmanagementbackend.enumerations.FishingAreaType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FishingArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "area_type")
    private FishingAreaType areaType;
    
    public FishingArea() {}
    
    public FishingArea(String name, FishingAreaType type) {
        this.name = name;
        this.areaType = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FishingAreaType getType() {
        return areaType;
    }

    public void setType(FishingAreaType type) {
        this.areaType = type;
    }

    @Override
    public String toString() {
        return "FishingArea [id=" + id + ", name=" + name + ", areaType=" + areaType + "]";
    }
    
    
}
