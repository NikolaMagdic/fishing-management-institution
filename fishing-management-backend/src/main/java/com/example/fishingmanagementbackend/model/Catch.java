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
import jakarta.persistence.OneToOne;

@Entity
public class Catch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private LocalDate time;
    
    @ManyToOne
    @JoinColumn(name = "fisherman_id", referencedColumnName = "id")
    private Fisherman fisherman;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "dailyCatch")
    private Set<CatchItem> catchItems = new HashSet<>();
    
    @OneToOne
    private Reservation reservation;
    
    public Catch() {}
    
    public Catch(LocalDate time, Set<CatchItem> catchItems) {
        this.time = time;
        this.catchItems = catchItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "DailyCatch [id=" + id + ", time=" + time + ", fisherman=" + fisherman + ", catchItems=" + catchItems
                + ", reservation=" + reservation + "]";
    }
    
    

}
