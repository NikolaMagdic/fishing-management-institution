package com.example.fishingmanagementbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "1")
public class ProfessionalFisherman extends Fisherman {
    
    @Column
    private int registryNumber;
    
    public ProfessionalFisherman() {
        super();
    }
    
    public ProfessionalFisherman(int registryNumber) {
        super();
        this.registryNumber = registryNumber;
    }

    public int getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(int registryNumber) {
        this.registryNumber = registryNumber;
    }

    private static final long serialVersionUID = 8149653669674771366L;

}
