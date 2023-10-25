package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

import com.example.fishingmanagementbackend.model.Reservation;

public class ReservationDTO {

    private LocalDate arrivalDate;
    
    private LocalDate departureDate;
    
    private Long fishingSpotId;
    
    private Long fishermanId;
    
    public ReservationDTO() {}

    public ReservationDTO(LocalDate arrivalDate, LocalDate departureDate,
            Long fishingSpotId, Long fishermanId) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.fishingSpotId = fishingSpotId;
        this.fishermanId = fishermanId;
    }
    
    public ReservationDTO(Reservation reservation) {
        this.arrivalDate = reservation.getArrivalDate();
        this.departureDate = reservation.getDepartureDate();
        this.fishingSpotId = reservation.getFishingSpot().getId();
        this.fishermanId = reservation.getFisherman().getId();
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

    public Long getFishingSpotId() {
        return fishingSpotId;
    }

    public void setFishingSpotId(Long fishingSpotId) {
        this.fishingSpotId = fishingSpotId;
    }

    public Long getFishermanId() {
        return fishermanId;
    }

    public void setFishermanId(Long fishermanId) {
        this.fishermanId = fishermanId;
    }

    @Override
    public String toString() {
        return "ReservationDTO [arrivalDate=" + arrivalDate + ", departureDate=" + departureDate + ", fishingSpotId="
                + fishingSpotId + ", fishermanId=" + fishermanId + "]";
    }


    
}
