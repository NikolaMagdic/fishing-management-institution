package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

import com.example.fishingmanagementbackend.model.Reservation;

public class ReservationDTO {

    private LocalDate arrivalDate;
    
    private LocalDate departureDate;
    
    private boolean cancelled;
    
    private Long fishingSpotId;
    
    private Long fishingAreaId;
    
    private Long fishermanId;
    
    private Long licenseId;
    
    public ReservationDTO() {}

    public ReservationDTO(LocalDate arrivalDate, LocalDate departureDate, boolean cancelled,
            Long fishingSpotId, Long fishingAreaId, Long fishermanId, Long licenseId) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.cancelled = cancelled;
        this.fishingSpotId = fishingSpotId;
        this.fishingAreaId = fishingAreaId;
        this.fishermanId = fishermanId;
        this.licenseId = licenseId;
    }
    
    public ReservationDTO(Reservation reservation) {
        this.arrivalDate = reservation.getArrivalDate();
        this.departureDate = reservation.getDepartureDate();
        this.cancelled = reservation.isCancelled();
        this.fishingSpotId = reservation.getFishingSpot().getId();
        this.fishingAreaId = reservation.getFishingSpot().getFishingArea().getId();
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
    
    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Long getFishingSpotId() {
        return fishingSpotId;
    }

    public void setFishingSpotId(Long fishingSpotId) {
        this.fishingSpotId = fishingSpotId;
    }

    public Long getFishingAreaId() {
        return fishingAreaId;
    }

    public void setFishingAreaId(Long fishingAreaId) {
        this.fishingAreaId = fishingAreaId;
    }

    public Long getFishermanId() {
        return fishermanId;
    }

    public void setFishermanId(Long fishermanId) {
        this.fishermanId = fishermanId;
    }
    
    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }

    @Override
    public String toString() {
        return "ReservationDTO [arrivalDate=" + arrivalDate + ", departureDate=" + departureDate + ", cancelled="
                + cancelled + ", fishingSpotId=" + fishingSpotId + ", fishingAreaId=" + fishingAreaId + ", fishermanId="
                + fishermanId + ", licenseId=" + licenseId + "]";
    }

    
}
