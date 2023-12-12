package com.example.fishingmanagementbackend.enumerations;

public enum FishingSpotType {
    ARRANGED_PLACE("Uređeno mesto"), BOAT("Čamac"), RAFT("Splav"), FISHING_HOUSE("Vikendica za ribolov");
    
    private String name;
    
    private FishingSpotType(String name) {
        this.name = name;
    }
    
    @Override   
    public String toString() {
        return name;
    }
    
}
