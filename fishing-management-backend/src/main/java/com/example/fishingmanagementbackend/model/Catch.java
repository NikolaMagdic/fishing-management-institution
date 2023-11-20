package com.example.fishingmanagementbackend.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Catch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "fisherman_id", referencedColumnName = "id")
    private Fisherman fisherman;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "dailyCatch")
    private Set<CatchItem> catchItems = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "fishing_area_id", referencedColumnName = "id")
    private FishingArea fishingArea;
    
    public Catch() {}
    
    public Catch(LocalDate date, Set<CatchItem> catchItems) {
        this.date = date;
        this.catchItems = catchItems;
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

    public Fisherman getFisherman() {
        return fisherman;
    }

    public void setFisherman(Fisherman fisherman) {
        this.fisherman = fisherman;
    }

    public Set<CatchItem> getCatchItems() {
        return catchItems;
    }

    public void setCatchItems(Set<CatchItem> catchItems) {
        this.catchItems = catchItems;
    }
    
    public FishingArea getFishingArea() {
        return fishingArea;
    }

    public void setFishingArea(FishingArea fishingArea) {
        this.fishingArea = fishingArea;
    }

    @Override
    public String toString() {
        return "Catch [id=" + id + ", date=" + date + ", fisherman=" + fisherman + ", catchItems=" + catchItems
                + ", fishingArea=" + fishingArea + "]";
    }
    

}
