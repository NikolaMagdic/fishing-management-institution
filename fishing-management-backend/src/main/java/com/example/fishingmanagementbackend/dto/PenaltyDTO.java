package com.example.fishingmanagementbackend.dto;

import com.example.fishingmanagementbackend.model.Penalty;

public class PenaltyDTO {

    private Long id;
    
    private String name;
    
    private String description;
    
    private double fine;
    
    public PenaltyDTO() {}
    
    public PenaltyDTO(Penalty penalty) {
        this.id = penalty.getId();
        this.name = penalty.getName();
        this.description = penalty.getDescription();
        this.fine = penalty.getFine();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }
    
    
}
