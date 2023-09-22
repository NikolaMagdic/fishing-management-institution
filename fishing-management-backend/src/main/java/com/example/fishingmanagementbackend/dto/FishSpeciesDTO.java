package com.example.fishingmanagementbackend.dto;

import com.example.fishingmanagementbackend.model.FishSpecies;

public class FishSpeciesDTO {
    
    private Long id;

    private String name;
    
    private String latinName;
    
    private String category;
    
    private int minSize;
    
    private int maxQuantity;
    
    private int fishingBanStartDay;
    
    private int fishingBanStartMonth;
    
    private int fishingBanEndDay;
    
    private int fishingBanEndMonth;
    
    private boolean permanentFishingBan;
    
    private String image;
    
    public FishSpeciesDTO() {
        
    }

    public FishSpeciesDTO(FishSpecies fishSpecies) {
        this.id = fishSpecies.getId();
        this.name = fishSpecies.getName();
        this.latinName = fishSpecies.getLatinName();
        this.category = fishSpecies.getCategory().toString();
        this.minSize = fishSpecies.getMinSize();
        this.maxQuantity = fishSpecies.getMaxQuantity();
        this.fishingBanStartDay = fishSpecies.getFishingBanStart().getDayOfMonth();
        this.fishingBanStartMonth = fishSpecies.getFishingBanStart().getMonth().getValue();
        this.fishingBanEndDay = fishSpecies.getFishingBanEnd().getDayOfMonth();
        this.fishingBanEndMonth = fishSpecies.getFishingBanEnd().getMonth().getValue();
        this.permanentFishingBan = fishSpecies.isPermanentFishingBan();
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getFishingBanStartDay() {
        return fishingBanStartDay;
    }

    public void setFishingBanStartDay(int fishingBanStartDay) {
        this.fishingBanStartDay = fishingBanStartDay;
    }

    public int getFishingBanStartMonth() {
        return fishingBanStartMonth;
    }

    public void setFishingBanStartMonth(int fishingBanStartMonth) {
        this.fishingBanStartMonth = fishingBanStartMonth;
    }

    public int getFishingBanEndDay() {
        return fishingBanEndDay;
    }

    public void setFishingBanEndDay(int fishingBanEndDay) {
        this.fishingBanEndDay = fishingBanEndDay;
    }

    public int getFishingBanEndMonth() {
        return fishingBanEndMonth;
    }

    public void setFishingBanEndMonth(int fishingBanEndMonth) {
        this.fishingBanEndMonth = fishingBanEndMonth;
    }

    public boolean isPermanentFishingBan() {
        return permanentFishingBan;
    }

    public void setPermanentFishingBan(boolean permanentFishingBan) {
        this.permanentFishingBan = permanentFishingBan;
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
                + ", image=" + image + "]";
    }
    
    
    
}
