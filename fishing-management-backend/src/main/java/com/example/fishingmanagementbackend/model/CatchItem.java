package com.example.fishingmanagementbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CatchItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private int quantity;
    
    @Column
    private double weight;
    
    @ManyToOne
    @JoinColumn(name = "catch_id", referencedColumnName = "id", nullable = false)
    private Catch dailyCatch;
    
    @ManyToOne
    @JoinColumn(name = "fish_id", referencedColumnName = "id", nullable = false)
    private FishSpecies fish;
    
    public CatchItem() {}
    
    public CatchItem(int quantity, double weight) {
        this.quantity = quantity;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public Catch getDailyCatch() {
        return dailyCatch;
    }

    public void setDailyCatch(Catch dailyCatch) {
        this.dailyCatch = dailyCatch;
    }

    public FishSpecies getFish() {
        return fish;
    }

    public void setFish(FishSpecies fish) {
        this.fish = fish;
    }

    @Override
    public String toString() {
        return "Catch [id=" + id + ", quantity=" + quantity + ", weight=" + weight + ", fish=" + fish + "]";
    }
    
    
    
}
