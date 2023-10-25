package com.example.fishingmanagementbackend.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private LocalDate arrivalDate;
    
    @Column
    private LocalDate departureDate;
    
    @ManyToOne()
    private FishingSpot fishingSpot;
    
    @ManyToOne()
    private Fisherman fisherman;
    
    public Reservation() {
        
    }
    
    public Reservation(LocalDate arrivalDate, LocalDate departureDate) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    
    public FishingSpot getFishingSpot() {
        return fishingSpot;
    }

    public void setFishingSpot(FishingSpot fishingSpot) {
        this.fishingSpot = fishingSpot;
    }

    public Fisherman getFisherman() {
        return fisherman;
    }

    public void setFisherman(Fisherman fisherman) {
        this.fisherman = fisherman;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", arrivalDate=" + arrivalDate + ", departureDate=" + departureDate
                + ", fishingSpot=" + fishingSpot + ", fisherman=" + fisherman + "]";
    }

    
    
    
}
