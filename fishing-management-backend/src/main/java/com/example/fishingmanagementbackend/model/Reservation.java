package com.example.fishingmanagementbackend.model;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalTime arrivalTime;
    
    @Column
    private LocalDate departureDate;
    
    @Column
    private LocalTime departureTime;
    
    @ManyToOne()
    private FishingSpot fishingSpot;
    
    @ManyToOne()
    private Fisherman fisherman;
    
    public Reservation() {
        
    }
    
    public Reservation(LocalDate arrivalDate, LocalTime arrivalTime, LocalDate departureDate, LocalTime departureTime) {
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
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

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
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
        return "Reservation [id=" + id + ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime
                + ", departureDate=" + departureDate + ", departureTime=" + departureTime + "]";
    }
    
    
}
