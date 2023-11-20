package com.example.fishingmanagementbackend.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "0")
public class RecreationalFisherman extends Fisherman {

    private static final long serialVersionUID = -3400494201807318971L;

    public RecreationalFisherman() {
        super();
    }
    
}
