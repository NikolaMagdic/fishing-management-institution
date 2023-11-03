package com.example.fishingmanagementbackend.model;

import java.io.Serializable;
import java.util.Objects;

public class CatchItemPK implements Serializable {

    private Long itemId;
    
    private Long dailyCatch;

    public CatchItemPK() { 
        
    }

    public CatchItemPK(Long itemId, Long dailyCatch) {
        super();
        this.itemId = itemId;
        this.dailyCatch = dailyCatch;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getDailyCatch() {
        return dailyCatch;
    }

    public void setDailyCatch(Long dailyCatch) {
        this.dailyCatch = dailyCatch;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(dailyCatch, itemId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CatchItemPK other = (CatchItemPK) obj;
        return Objects.equals(dailyCatch, other.dailyCatch) && Objects.equals(itemId, other.itemId);
    }

    private static final long serialVersionUID = 941781577775048290L;
}
