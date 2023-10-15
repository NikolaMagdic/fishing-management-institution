package com.example.fishingmanagementbackend.model;

import com.example.fishingmanagementbackend.enumerations.CatchItemStatus;

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
    private Long itemId;
    
    @Column
    private int quantity;
    
    @Column
    private double weight;
    
    @Column
    private CatchItemStatus confirmationStatus;
    
    @ManyToOne
    @JoinColumn(name = "catch_id", referencedColumnName = "id", nullable = false)
    private Catch dailyCatch;
    
    @ManyToOne
    @JoinColumn(name = "fish_id", referencedColumnName = "id", nullable = false)
    private FishSpecies fish;
    
    public CatchItem() {}
    
    public CatchItem(int quantity, double weight, CatchItemStatus status) {
        this.quantity = quantity;
        this.weight = weight;
        this.confirmationStatus = status;
    }

    public Long getId() {
        return itemId;
    }

    public void setId(Long itemId) {
        this.itemId = itemId;
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

    public CatchItemStatus getStatus() {
        return confirmationStatus;
    }

    public void setStatus(CatchItemStatus status) {
        this.confirmationStatus = status;
    }

    @Override
    public String toString() {
        return "CatchItem [itemId=" + itemId + ", quantity=" + quantity + ", weight=" + weight + ", status=" + confirmationStatus + ", dailyCatch=" + dailyCatch + ", fish=" + fish + "]";
    }

    
}
