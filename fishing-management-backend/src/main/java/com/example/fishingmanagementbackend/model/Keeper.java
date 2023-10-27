package com.example.fishingmanagementbackend.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Keeper extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManyToMany
    @JoinTable(name = "keeps",
               joinColumns = @JoinColumn(name = "keeper_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "fishing_area_id", referencedColumnName = "id"))
    private Set<FishingArea> fishingAreas = new HashSet<>();
    
    public Keeper() {}
    
    public Set<FishingArea> getFishingAreas() {
        return fishingAreas;
    }

    public void setFishingAreas(Set<FishingArea> fishingAreas) {
        this.fishingAreas = fishingAreas;
    }

    @Override
    public String toString() {
        return "Keeper [fishingAreas=" + fishingAreas + "]";
    }

    
    
}
