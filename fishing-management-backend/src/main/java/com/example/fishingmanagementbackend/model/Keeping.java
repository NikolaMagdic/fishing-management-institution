package com.example.fishingmanagementbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(KeepingPK.class)
public class Keeping {

    @Id
    @ManyToOne
    @JoinColumn(name = "keeper_id", referencedColumnName = "id", nullable = false)
    private Keeper keeper;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "fishing_area_id", referencedColumnName = "id", nullable = false)
    private FishingArea area;
    
    public Keeping() {}

    public Keeper getKeeper() {
        return keeper;
    }

    public void setKeeper(Keeper keeper) {
        this.keeper = keeper;
    }

    public FishingArea getArea() {
        return area;
    }

    public void setArea(FishingArea area) {
        this.area = area;
    }
    
    
}
