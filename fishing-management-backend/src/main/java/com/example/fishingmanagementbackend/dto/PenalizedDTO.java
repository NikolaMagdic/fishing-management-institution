package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

import com.example.fishingmanagementbackend.model.Penalized;

public class PenalizedDTO {

    private Long id;
    
    private String report;
    
    private LocalDate date;
    
    private Long fishermanId;
    
    private Long penaltyId;
    
    private String penaltyName;
    
    private double penaltyFine;
    
    private String keeperName;
    
    private Long areaId;
    
    private String areaName;
    
    public PenalizedDTO() { }
    
    public PenalizedDTO(Penalized penalized) {
        this.id = penalized.getId();
        this.report = penalized.getReport();
        this.date = penalized.getDate();
        this.fishermanId = penalized.getId();
        this.penaltyId = penalized.getId();
        this.penaltyName = penalized.getPenalty().getName();
        this.penaltyFine = penalized.getPenalty().getFine();
        this.keeperName = penalized.getKeeping().getKeeper().getFirstName() + " " + penalized.getKeeping().getKeeper().getLastName();
        this.areaId = penalized.getKeeping().getArea().getId();
        this.areaName = penalized.getKeeping().getArea().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getFishermanId() {
        return fishermanId;
    }

    public void setFishermanId(Long fishermanId) {
        this.fishermanId = fishermanId;
    }

    public Long getPenaltyId() {
        return penaltyId;
    }

    public void setPenaltyId(Long penaltyId) {
        this.penaltyId = penaltyId;
    }

    public String getPenaltyName() {
        return penaltyName;
    }

    public void setPenaltyName(String penaltyName) {
        this.penaltyName = penaltyName;
    }

    public double getPenaltyFine() {
        return penaltyFine;
    }

    public void setPenaltyFine(double penaltyFine) {
        this.penaltyFine = penaltyFine;
    }

    public String getKeeperName() {
        return keeperName;
    }

    public void setKeeperName(String keeperName) {
        this.keeperName = keeperName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    
}
