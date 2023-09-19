package com.example.fishingmanagementbackend.model;

import java.util.Date;
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
    private Date fishingBanStart;
    
    @Column
    private Date fishingBanEnd;
    
    @Column
    private boolean permanentFishingBan;
    
    private String image;

    @ManyToMany(mappedBy = "fishSpecies")
    private Set<FishingArea> fishingAreas = new HashSet<>();
    
    public FishSpecies() {
        
    }
    
    public FishSpecies(String name, String latinName, FishCategory category, int minSize, int maxQuantity,
            Date fishingBanStart, Date fishingBanEnd, boolean permanentFishingBan, String image) {
        this.name = name;
        this.latinName = latinName;
        this.category = category;
        this.minSize = minSize;
        this.maxQuantity = maxQuantity;
        this.fishingBanStart = fishingBanStart;
        this.fishingBanEnd = fishingBanEnd;
        this.permanentFishingBan = permanentFishingBan;
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

    public Date getFishingBanStart() {
        return fishingBanStart;
    }

    public void setFishingBanStart(Date fishingBanStart) {
        this.fishingBanStart = fishingBanStart;
    }

    public Date getFishingBanEnd() {
        return fishingBanEnd;
    }

    public void setFishingBanEnd(Date fishingBanEnd) {
        this.fishingBanEnd = fishingBanEnd;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FishSpecies [id=" + id + ", name=" + name + ", latinName=" + latinName + ", category=" + category
                + ", minSize=" + minSize + ", maxQuantity=" + maxQuantity + ", fishingBanStart=" + fishingBanStart
                + ", fishingBanEnd=" + fishingBanEnd + ", permanentFishingBan=" + permanentFishingBan + ", image="
                + image + ", fishingAreas=" + fishingAreas + "]";
    }

}
