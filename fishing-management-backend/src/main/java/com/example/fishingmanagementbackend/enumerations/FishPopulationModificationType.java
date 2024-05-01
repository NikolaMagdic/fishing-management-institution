package com.example.fishingmanagementbackend.enumerations;

public enum FishPopulationModificationType {

    FISH_STOCKING("Poribljavanje"), SELECTIVE_FISHING("Selektivni ribolov");
    
    private String name;
    
    private FishPopulationModificationType(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
