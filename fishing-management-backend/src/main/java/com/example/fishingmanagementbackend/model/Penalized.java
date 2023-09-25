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
public class Penalized {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "fisherman_id", referencedColumnName = "id")
    private Fisherman fisherman;
    
    @ManyToOne
    @JoinColumn(name = "penalty_id", referencedColumnName = "id")
    private Penalty penalty;
    
    public Penalized() {}
    
    public Penalized(LocalDate date, Fisherman fisherman, Penalty penalty) {
        this.date = date;
        this.fisherman = fisherman;
        this.penalty = penalty;
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

    public Penalty getPenalty() {
        return penalty;
    }

    public void setPenalty(Penalty penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "Penalized [id=" + id + ", date=" + date + ", fisherman=" + fisherman + ", penalty=" + penalty + "]";
    }
    
    
    
}
