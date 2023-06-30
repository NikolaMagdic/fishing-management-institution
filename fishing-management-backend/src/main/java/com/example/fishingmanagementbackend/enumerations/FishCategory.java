package com.example.fishingmanagementbackend.enumerations;

public enum FishCategory {

    NOBLE("Plemenita"), INDIGENOUS("Autohtona"), NON_NATIVE("Alohtona");
    
    private String name;
    
    private FishCategory(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
      return name;
    }
}
