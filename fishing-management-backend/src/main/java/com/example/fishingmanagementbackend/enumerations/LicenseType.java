package com.example.fishingmanagementbackend.enumerations;

public enum LicenseType {
    YEARLY("Godišnja"), DAILY("Dnevna"), MULTIDAY("Višednevna"); 
    
    private String name;
    
    private LicenseType(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
