package com.example.fishingmanagementbackend.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FishStocking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private LocalDate date;
    
    @Column
    private int number;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "fishing_area_id", referencedColumnName = "id")
    private FishingArea fishingArea;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "fish_species_id", referencedColumnName = "id")
    private FishSpecies fishSpecies;
    
    public FishStocking() {}

    public FishStocking(LocalDate date, int number) {
        this.date = date;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        return "FishStocking [id=" + id + ", date=" + date + ", number=" + number + ", fishingArea=" + fishingArea
                + ", fishSpecies=" + fishSpecies + "]";
    }
    
    
    
}
