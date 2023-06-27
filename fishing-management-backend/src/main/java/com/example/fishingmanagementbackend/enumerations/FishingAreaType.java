package com.example.fishingmanagementbackend.enumerations;

public enum FishingAreaType {

    RIVER("Reka"), LAKE("Jezero"), POND("Bara");
    
    private String name;
    
    private FishingAreaType(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
      return name;
    }
}
