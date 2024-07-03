package com.example.fishingmanagementbackend.model;

import com.example.fishingmanagementbackend.enumerations.CatchItemStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@IdClass(CatchItemPK.class)
@SequenceGenerator(name = "item_sequence_generator", sequenceName = "catch_item_id", initialValue = 1, allocationSize = 1)
public class CatchItem {

    // Kod kompozitnih kljuceva se mora koristiti sekvenca jer IDENTITY nacin generisanja kljuceva ne radi
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence_generator")
    private Long itemId;
    
    @Column
    private int quantity;
    
    @Column
    private double weight;
    
    @Column
    @Enumerated(EnumType.STRING)
    private CatchItemStatus confirmationStatus;
    
    // Strani kljuc je deo primarnog kljuca
    @ManyToOne
    @Id
    @JoinColumn(name = "catch_id", referencedColumnName = "id", nullable = false)
    private Catch dailyCatch;
    
    @ManyToOne
    @JoinColumn(name = "fish_id", referencedColumnName = "id", nullable = false)
    private FishSpecies fish;
    
    @ManyToOne
    @JoinColumn(name = "keeper_id", referencedColumnName = "id")
    private Keeper keeper;
    
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

    public Keeper getKeeper() {
        return keeper;
    }

    public void setKeeper(Keeper keeper) {
        this.keeper = keeper;
    }

    @Override
    public String toString() {
        return "CatchItem [itemId=" + itemId + ", quantity=" + quantity + ", weight=" + weight + ", status=" + confirmationStatus + ", dailyCatch=" + dailyCatch + ", fish=" + fish + "]";
    }

    
}
