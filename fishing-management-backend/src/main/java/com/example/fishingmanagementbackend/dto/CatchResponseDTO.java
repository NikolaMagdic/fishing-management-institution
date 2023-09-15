package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.fishingmanagementbackend.model.Catch;
import com.example.fishingmanagementbackend.model.CatchItem;

public class CatchResponseDTO {

    private Long id;
    
    private String fishingAreaName;
    
    private LocalDate date;
    
    private Set<CatchItemResponseDTO> catchItemsResponse = new HashSet<>();
    
    public CatchResponseDTO() {}
    
    public CatchResponseDTO(String fishingAreaName, LocalDate date, Set<CatchItemResponseDTO> catchItemsResponse) {
        this.fishingAreaName = fishingAreaName;
        this.date = date;
        this.catchItemsResponse = catchItemsResponse;
    }
    
    public CatchResponseDTO(Catch dailyCatch) {
        this.id = dailyCatch.getId();
        this.fishingAreaName = dailyCatch.getFishingArea().getName();
        this.date = dailyCatch.getTime();
        this.catchItemsResponse = transformCatchItemsToDTO(dailyCatch.getCatchItems());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFishingAreaName() {
        return fishingAreaName;
    }

    public void setFishingAreaName(String fishingAreaName) {
        this.fishingAreaName = fishingAreaName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<CatchItemResponseDTO> getCatchItemsResponse() {
        return catchItemsResponse;
    }

    public void setCatchItemsResponse(Set<CatchItemResponseDTO> catchItemsResponse) {
        this.catchItemsResponse = catchItemsResponse;
    }
    
    private Set<CatchItemResponseDTO> transformCatchItemsToDTO(Set<CatchItem> catches) {
        Set<CatchItemResponseDTO> catchesDTO = new HashSet<>();
        for(CatchItem c : catches) {
           CatchItemResponseDTO cDTO = new CatchItemResponseDTO(c.getId(), c.getQuantity(), c.getWeight(), c.isConfirmed(), c.getFish().getName()); 
           catchesDTO.add(cDTO);
        }
        return catchesDTO;
    }

}
