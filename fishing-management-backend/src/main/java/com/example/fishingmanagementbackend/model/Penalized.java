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

@Entity
public class Penalized {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private LocalDate date;
    
    @Column
    private String report;
    
    @ManyToOne
    @JoinColumn(name = "fisherman_id", referencedColumnName = "id")
    private Fisherman fisherman;
    
    @ManyToOne
    @JoinColumn(name = "penalty_id", referencedColumnName = "id")
    private Penalty penalty;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "keeper_id", referencedColumnName = "keeper_id"),
        @JoinColumn(name = "area_id", referencedColumnName = "fishing_area_id")
    })
    private Keeping keeping;
    
    public Penalized() {}
    
    public Penalized(LocalDate date, String report, Fisherman fisherman, Penalty penalty) {
        this.date = date;
        this.report = report;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Keeping getKeeping() {
        return keeping;
    }

    public void setKeeping(Keeping keeping) {
        this.keeping = keeping;
    }

    @Override
    public String toString() {
        return "Penalized [id=" + id + ", date=" + date + ", report=" + report + ", fisherman=" + fisherman
                + ", penalty=" + penalty + "]";
    }
    
    
}
