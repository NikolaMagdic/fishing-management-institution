package com.example.fishingmanagementbackend.model;

import java.util.HashSet;
import java.util.Set;

import com.example.fishingmanagementbackend.enumerations.FishCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class FishSpecies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private String latinName;
    
    @Column
    private FishCategory category;
    
    @Column
    private int minSize;
    
    @Column
    private int maxQuantity;
    
    // mozda izdvojiti ovo u klasu FishingBan, da se ne buni SonarLint
    @Column
    private int fishingBanStartDay;
    
    @Column
    private int fishingBanStartMonth;
    
    @Column
    private int fishingBanEndDay;
    
    @Column
    private int fishingBanEndMonth;
    
    @Column
    private boolean permanentFishingBan;
    
    private String description;
    
    @Column
    private String image;

    @ManyToMany(mappedBy = "fishSpecies")
    private Set<FishingArea> fishingAreas = new HashSet<>();
    
    public FishSpecies() {
        
    }
    
    public FishSpecies(String name, String latinName, FishCategory category, int minSize, int maxQuantity,
            int fishingBanStartDay, int fishingBanStartMonth, int fishingBanEndDay, int fishingBanEndMonth, boolean permanentFishingBan, String description, String image) {
        this.name = name;
        this.latinName = latinName;
        this.category = category;
        this.minSize = minSize;
        this.maxQuantity = maxQuantity;
        this.fishingBanStartDay = fishingBanStartDay;
        this.fishingBanStartMonth = fishingBanStartMonth;
        this.fishingBanEndDay = fishingBanEndDay;
        this.fishingBanEndMonth = fishingBanEndMonth;
        this.permanentFishingBan = permanentFishingBan;
        this.description = description;
        this.image = image;
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

    public Set<FishingArea> getFishingAreas() {
        return fishingAreas;
    }

    public void setFishingAreas(Set<FishingArea> fishingAreas) {
        this.fishingAreas = fishingAreas;
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
        return "FishSpecies [id=" + id + ", name=" + name + ", latinName=" + latinName + ", category=" + category
                + ", minSize=" + minSize + ", maxQuantity=" + maxQuantity + ", fishingBanStartDay=" + fishingBanStartDay
                + ", fishingBanStartMonth=" + fishingBanStartMonth + ", fishingBanEndDay=" + fishingBanEndDay
                + ", fishingBanEndMonth=" + fishingBanEndMonth + ", permanentFishingBan=" + permanentFishingBan
                + ", description=" + description + ", image=" + image + ", fishingAreas=" + fishingAreas + "]";
    }


}
