package com.example.fishingmanagementbackend.enumerations;

public enum FishermanCategory {

    RECREATIONAL("Rekreativni"), PROFESSIONAL("Profesionalni");
    
    private String name;
    
    private FishermanCategory(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
      return name;
    }
}
