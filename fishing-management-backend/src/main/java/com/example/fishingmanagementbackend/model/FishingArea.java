package com.example.fishingmanagementbackend.model;

import java.util.HashSet;
import java.util.Set;

import com.example.fishingmanagementbackend.enumerations.FishingAreaType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class FishingArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "area_type")
    private FishingAreaType areaType;
    
    @ManyToMany
    @JoinTable(name = "containing", 
                joinColumns = @JoinColumn(name = "fish_species_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "fishing_area_id", referencedColumnName = "id"))
    private Set<FishSpecies> fishSpecies = new HashSet<>();
    
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
    
    public Set<FishSpecies> getFishSpecies() {
        return fishSpecies;
    }

    public void setFishSpecies(Set<FishSpecies> fishSpecies) {
        this.fishSpecies = fishSpecies;
    }

    @Override
    public String toString() {
        return "FishingArea [id=" + id + ", name=" + name + ", areaType=" + areaType + "]";
    }
    
    
}
