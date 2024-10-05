package com.example.fishingmanagementbackend.model;

import java.time.LocalDate;

import com.example.fishingmanagementbackend.enumerations.FishPopulationModificationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FishPopulationModification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private LocalDate date;
    
    @Column
    private Integer totalWeight;
    
    @Column
    private Integer amount;
    
    @Column
    @Enumerated(EnumType.STRING)
    private FishPopulationModificationType modificationType;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "fishing_area_id", referencedColumnName = "id")
    private FishingArea fishingArea;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "fish_species_id", referencedColumnName = "id")
    private FishSpecies fishSpecies;
    
    public FishPopulationModification() {}

    public FishPopulationModification(LocalDate date, Integer totalWeight, Integer amount, FishPopulationModificationType modificationType) {
        this.date = date;
        this.totalWeight = totalWeight;
        this.amount = amount;
        this.modificationType = modificationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public FishPopulationModificationType getModificationType() {
        return modificationType;
    }

    public void setModificationType(FishPopulationModificationType modificationType) {
        this.modificationType = modificationType;
    }

    public FishingArea getFishingArea() {
        return fishingArea;
    }

    public void setFishingArea(FishingArea fishingArea) {
        this.fishingArea = fishingArea;
    }

    public FishSpecies getFishSpecies() {
        return fishSpecies;
    }

    public void setFishSpecies(FishSpecies fishSpecies) {
        this.fishSpecies = fishSpecies;
    }

    @Override
    public String toString() {
        return "FishPopulationModification [id=" + id + ", date=" + date + ", totalWeight=" + totalWeight + ", amount="
                + amount + ", modificationType=" + modificationType + ", fishingArea=" + fishingArea + ", fishSpecies="
                + fishSpecies + "]";
    }

    
}
