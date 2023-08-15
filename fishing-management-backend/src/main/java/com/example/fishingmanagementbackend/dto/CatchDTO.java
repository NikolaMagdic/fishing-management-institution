package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.fishingmanagementbackend.model.CatchItem;
import com.example.fishingmanagementbackend.model.Catch;

public class CatchDTO {

    private Long id;
    
    private Long fishingAreaId;
    
    private LocalDate date;
    
    private Set<CatchItemDTO> catchItems = new HashSet<>();
    
    public CatchDTO() {}
    
    public CatchDTO(Long fishingAreaId, LocalDate date, Set<CatchItemDTO> catchItems) {
        this.fishingAreaId = fishingAreaId;
        this.date = date;
        this.catchItems = catchItems;
    }
    
    public CatchDTO(Catch dailyCatch) {
        this.id = dailyCatch.getId();
        this.fishingAreaId = dailyCatch.getFishingArea().getId();
        this.date = dailyCatch.getTime();
        this.catchItems = transformCatchItemsToDTO(dailyCatch.getCatchItems());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getFishingAreaId() {
        return fishingAreaId;
    }

    public void setFishingAreaId(Long fishingAreaId) {
        this.fishingAreaId = fishingAreaId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<CatchItemDTO> getCatchItems() {
        return catchItems;
    }

    public void setCatchItems(Set<CatchItemDTO> catchItems) {
        this.catchItems = catchItems;
    }

    @Override
    public String toString() {
        return "CatchDTO [id=" + id + ", fishingAreaId=" + fishingAreaId + ", date=" + date + ", catchItems=" + catchItems + "]";
    }
    
    
    private Set<CatchItemDTO> transformCatchItemsToDTO(Set<CatchItem> catches) {
        Set<CatchItemDTO> catchesDTO = new HashSet<>();
        for (CatchItem c : catches) {
            CatchItemDTO cDTO = new CatchItemDTO(c.getQuantity(), c.getWeight(), c.getFish().getId());
            catchesDTO.add(cDTO);
        }
        return catchesDTO;
    }
    
}
