package com.example.fishingmanagementbackend.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private LocalDate arrivalDate;
    
    @Column
    private LocalDate departureDate;
    
    @Column
    private boolean cancelled;
    
    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "fishing_area_id", referencedColumnName = "fishing_area_id"),
        @JoinColumn(name = "fishing_spot_id", referencedColumnName = "id")
    })
    private FishingSpot fishingSpot;
    
    @ManyToOne(optional = false)
    private RecreationalFisherman fisherman;
    
    // Polje potrebno zbog otkazivanja rezervisanih termina ukoliko je zahtev za dnevnu ili visednevnu dozvolu odbijen
    @OneToOne(optional = true)
    @JoinColumn(name = "license_id")
    private License license;
    
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
    
    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public FishingSpot getFishingSpot() {
        return fishingSpot;
    }

    public void setFishingSpot(FishingSpot fishingSpot) {
        this.fishingSpot = fishingSpot;
    }

    public RecreationalFisherman getFisherman() {
        return fisherman;
    }

    public void setFisherman(RecreationalFisherman fisherman) {
        this.fisherman = fisherman;
    }
    
    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", arrivalDate=" + arrivalDate + ", departureDate=" + departureDate
                + ", cancelled=" + cancelled + ", fishingSpot=" + fishingSpot + ", fisherman=" + fisherman
                + ", license=" + license + "]";
    }

}
