package com.example.fishingmanagementbackend.enumerations;

import java.util.Arrays;
import java.util.Optional;

public enum FishingSpotType {
    ARRANGED_PLACE("Uređeno mesto"), BOAT("Čamac"), PIER("Splav"), FISHING_HOUSE("Vikendica za ribolov");
    
    private String name;
    
    private FishingSpotType(String name) {
        this.name = name;
    }
    
    @Override   
    public String toString() {
        return name;
    }
    
    public static Optional<FishingSpotType> getType(String name) {
        return Arrays.stream(values())
                .filter(ft -> ft.name.equalsIgnoreCase(name))
                .findFirst();
    }
    
}
