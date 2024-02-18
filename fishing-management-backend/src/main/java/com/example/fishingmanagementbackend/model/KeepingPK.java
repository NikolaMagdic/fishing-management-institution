package com.example.fishingmanagementbackend.model;

import java.io.Serializable;
import java.util.Objects;

public class KeepingPK implements Serializable {

    private Long keeper;
    
    private Long area;
    
    public KeepingPK() { }
    
    public KeepingPK(Long keeper, Long area) {
        this.keeper = keeper;
        this.area = area;
    } 
    
    public Long getKeeperId() {
        return keeper;
    }

    public void setKeeperId(Long keeperId) {
        this.keeper = keeperId;
    }

    public Long getAreaId() {
        return area;
    }

    public void setAreaId(Long areaId) {
        this.area = areaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, keeper);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KeepingPK other = (KeepingPK) obj;
        return Objects.equals(area, other.area) && Objects.equals(keeper, other.keeper);
    }

    private static final long serialVersionUID = 6399511282948594466L;

    
}
