package com.example.fishingmanagementbackend.dto;

import com.example.fishingmanagementbackend.enumerations.FishCategory;
import com.example.fishingmanagementbackend.model.FishSpecies;

public class FishSpeciesDTO {
    
    private Long id;

    private String name;
    
    private String latinName;
    
    private FishCategory category;
    
    private Integer minSize;
    
    private Integer maxQuantity;
    
    private Integer fishingBanStartDay;
    
    private Integer fishingBanStartMonth;
    
    private Integer fishingBanEndDay;
    
    private Integer fishingBanEndMonth;
    
    private boolean permanentFishingBan;
    
    private String description;
    
    private String image;
    
    public FishSpeciesDTO() {
        
    }

    public FishSpeciesDTO(FishSpecies fishSpecies) {
        this.id = fishSpecies.getId();
        this.name = fishSpecies.getName();
        this.latinName = fishSpecies.getLatinName();
        this.category = fishSpecies.getCategory();
        this.minSize = fishSpecies.getMinSize();
        this.maxQuantity = fishSpecies.getMaxQuantity();
        this.fishingBanStartDay = fishSpecies.getFishingBanStartDay(); 
        this.fishingBanStartMonth = fishSpecies.getFishingBanStartMonth();
        this.fishingBanEndDay = fishSpecies.getFishingBanEndDay();
        this.fishingBanEndMonth = fishSpecies.getFishingBanEndMonth();
        this.permanentFishingBan = fishSpecies.isPermanentFishingBan();
        this.description = fishSpecies.getDescription();
        this.image = fishSpecies.getImage();
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

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public FishCategory getCategory() {
        return category;
    }

    public void setCategory(FishCategory category) {
        this.category = category;
    }

    public Integer getMinSize() {
        return minSize;
    }

    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getFishingBanStartDay() {
        return fishingBanStartDay;
    }

    public void setFishingBanStartDay(Integer fishingBanStartDay) {
        this.fishingBanStartDay = fishingBanStartDay;
    }

    public Integer getFishingBanStartMonth() {
        return fishingBanStartMonth;
    }

    public void setFishingBanStartMonth(Integer fishingBanStartMonth) {
        this.fishingBanStartMonth = fishingBanStartMonth;
    }

    public Integer getFishingBanEndDay() {
        return fishingBanEndDay;
    }

    public void setFishingBanEndDay(Integer fishingBanEndDay) {
        this.fishingBanEndDay = fishingBanEndDay;
    }

    public Integer getFishingBanEndMonth() {
        return fishingBanEndMonth;
    }

    public void setFishingBanEndMonth(Integer fishingBanEndMonth) {
        this.fishingBanEndMonth = fishingBanEndMonth;
    }

    public boolean isPermanentFishingBan() {
        return permanentFishingBan;
    }

    public void setPermanentFishingBan(boolean permanentFishingBan) {
        this.permanentFishingBan = permanentFishingBan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FishSpeciesDTO [id=" + id + ", name=" + name + ", latinName=" + latinName + ", category=" + category
                + ", minSize=" + minSize + ", maxQuantity=" + maxQuantity + ", fishingBanStartDay=" + fishingBanStartDay
                + ", fishingBanStartMonth=" + fishingBanStartMonth + ", fishingBanEndDay=" + fishingBanEndDay
                + ", fishingBanEndMonth=" + fishingBanEndMonth + ", permanentFishingBan=" + permanentFishingBan
                + ", description=" + description + ", image=" + image + "]";
    }
    
    
}
