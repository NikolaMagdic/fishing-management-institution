package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

import com.example.fishingmanagementbackend.model.Reservation;

public class ReservationResponseDTO {

    private Long reservationId;
    
    private String fishingAreaName;
    
    private Long fishingSpotId;
    
    private String fishingSpotType;
    
    private String fishermanFirstName;
    
    private String fishermanLastName;
    
    private LocalDate date;
    
    public ReservationResponseDTO() {}
    
    public ReservationResponseDTO(Reservation reservation) {
        this.reservationId = reservation.getId();
        this.fishingAreaName = reservation.getFishingSpot().getFishingArea().getName();
        this.fishingSpotId = reservation.getFishingSpot().getId();
        this.fishingSpotType = reservation.getFishingSpot().getType().toString();
        this.fishermanFirstName = reservation.getFisherman().getFirstName();
        this.fishermanLastName = reservation.getFisherman().getLastName();
        this.date = reservation.getArrivalDate();
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getFishingAreaName() {
        return fishingAreaName;
    }

    public void setFishingAreaName(String fishingAreaName) {
        this.fishingAreaName = fishingAreaName;
    }

    public Long getFishingSpotId() {
        return fishingSpotId;
    }

    public void setFishingSpotId(Long fishingSpotId) {
        this.fishingSpotId = fishingSpotId;
    }

    public String getFishingSpotType() {
        return fishingSpotType;
    }

    public void setFishingSpotType(String fishingSpotType) {
        this.fishingSpotType = fishingSpotType;
    }

    public String getFishermanFirstName() {
        return fishermanFirstName;
    }

    public void setFishermanFirstName(String fishermanFirstName) {
        this.fishermanFirstName = fishermanFirstName;
    }

    public String getFishermanLastName() {
        return fishermanLastName;
    }

    public void setFishermanLastName(String fishermanLastName) {
        this.fishermanLastName = fishermanLastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    
    
    
}
