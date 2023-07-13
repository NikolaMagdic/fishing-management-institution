package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDTO {

    private LocalDate arrivalDate;
    
    private LocalTime arrivalTime;
    
    private LocalDate departureDate;
    
    private LocalTime departureTime;
    
    private Long fishingSpotId;
    
    private Long fishermanId;
    
    public ReservationDTO() {}

    public ReservationDTO(LocalDate arrivalDate, LocalTime arrivalTime, LocalDate departureDate,
            LocalTime departureTime, Long fishingSpotId, Long fishermanId) {
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.fishingSpotId = fishingSpotId;
        this.fishermanId = fishermanId;
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
        return "ReservationDTO [arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime + ", departureDate="
                + departureDate + ", departureTime=" + departureTime + ", fishingSpotId=" + fishingSpotId
                + ", fishermanId=" + fishermanId + "]";
    }
    
    
}
