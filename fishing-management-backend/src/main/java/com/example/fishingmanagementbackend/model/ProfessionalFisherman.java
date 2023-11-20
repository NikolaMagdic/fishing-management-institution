package com.example.fishingmanagementbackend.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "1")
public class ProfessionalFisherman extends Fisherman {
    
    public ProfessionalFisherman() {
        super();
    }
    
    private static final long serialVersionUID = 8149653669674771366L;

}
