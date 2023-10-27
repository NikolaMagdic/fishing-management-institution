package com.example.fishingmanagementbackend.model;

import java.io.Serializable;

import com.example.fishingmanagementbackend.enumerations.FishermanCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Fisherman extends User implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1741978761255913407L;

    @Column
    private String address;
    
    @Column
    private String city;
    
    @Column
    private FishermanCategory category;
    
    public Fisherman() {}

    public Fisherman(String address, String city,
            FishermanCategory fishermanCategory) {
        super();
        this.address = address;
        this.city = city;
        this.category = fishermanCategory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public FishermanCategory getCategory() {
        return category;
    }

    public void setCategory(FishermanCategory fishermanCategory) {
        this.category = fishermanCategory;
    }

    @Override
    public String toString() {
        return "Fisherman [address=" + address + ", city=" + city + ", category=" + category + "]";
    }
    
    
}
