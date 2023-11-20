package com.example.fishingmanagementbackend.model;

import java.util.HashSet;
import java.util.Set;

import com.example.fishingmanagementbackend.enumerations.FishingAreaType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "area_type")
    @Enumerated(EnumType.ORDINAL)
    private FishingAreaType areaType;
    
    // trenutno su slike samo String sa linkom
    @Column(name = "image")
    private String image;
    
    @ManyToMany
    @JoinTable(name = "containing", 
                joinColumns = @JoinColumn(name = "fishing_area_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "fish_species_id", referencedColumnName = "id"))
    private Set<FishSpecies> fishSpecies = new HashSet<>();
    
    public FishingArea() {}
    
    public FishingArea(String name, String description, FishingAreaType type, String image) {
        this.name = name;
        this.description = description;
        this.areaType = type;
        this.image = image;
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
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FishingAreaType getType() {
        return areaType;
    }

    public void setType(FishingAreaType type) {
        this.areaType = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<FishSpecies> getFishSpecies() {
        return fishSpecies;
    }

    public void setFishSpecies(Set<FishSpecies> fishSpecies) {
        this.fishSpecies = fishSpecies;
    }

    @Override
    public String toString() {
        return "FishingArea [id=" + id + ", name=" + name + ", description=" + description + ", areaType=" + areaType + ", image" + image + "]";
    }
    
    
}
