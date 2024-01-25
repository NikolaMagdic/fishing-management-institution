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
    private Integer minSize;
    
    @Column
    private Integer maxQuantity;
    
    // mozda izdvojiti ovo u klasu FishingBan, da se ne buni SonarLint
    @Column(nullable = true)
    private Integer fishingBanStartDay;
    
    @Column(nullable = true)
    private Integer fishingBanStartMonth;
    
    @Column(nullable = true)
    private Integer fishingBanEndDay;
    
    @Column(nullable = true)
    private Integer fishingBanEndMonth;
    
    @Column
    private boolean permanentFishingBan;
    
    private String description;
    
    @Column
    private String image;

    @ManyToMany(mappedBy = "fishSpecies")
    private Set<FishingArea> fishingAreas = new HashSet<>();
    
    public FishSpecies() {
        
    }
    
    public FishSpecies(String name, String latinName, FishCategory category, Integer minSize, Integer maxQuantity,
            Integer fishingBanStartDay, Integer fishingBanStartMonth, Integer fishingBanEndDay, Integer fishingBanEndMonth, boolean permanentFishingBan, String description, String image) {
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
