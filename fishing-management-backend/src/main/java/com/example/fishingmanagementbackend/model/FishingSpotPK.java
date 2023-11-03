package com.example.fishingmanagementbackend.model;

import java.io.Serializable;
import java.util.Objects;

public class FishingSpotPK implements Serializable {

    private Long id;
    
    private Long fishingArea;
    
    public FishingSpotPK() { }
    
    public FishingSpotPK(Long id, Long fishingArea) {
        this.id = id;
        this.fishingArea = fishingArea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFishingArea() {
        return fishingArea;
    }

    public void setFishingArea(Long fishingArea) {
        this.fishingArea = fishingArea;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fishingArea, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FishingSpotPK other = (FishingSpotPK) obj;
        return Objects.equals(fishingArea, other.fishingArea) && Objects.equals(id, other.id);
    }

    private static final long serialVersionUID = 7592675116362615023L;

}
