package com.example.fishingmanagementbackend.dto;

import java.util.Date;

import com.example.fishingmanagementbackend.enumerations.FishermanCategory;
import com.example.fishingmanagementbackend.model.Fisherman;

public class FishermanDTO {
    
    private Long id;

    private String firstName;
    
    private String lastName;
    
    private Date dateOfBirth;
    
    private String address;
    
    private String city;
    
    private FishermanCategory category;
    
    public FishermanDTO() {}
    
    public FishermanDTO(Fisherman fisherman) {
        this.id = fisherman.getId();
        this.firstName = fisherman.getFirstName();
        this.lastName = fisherman.getLastName();
        this.dateOfBirth = fisherman.getDateOfBirth();
        this.address = fisherman.getAddress();
        this.city = fisherman.getCity();
        this.category = fisherman.getCategory();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public FishermanCategory getCategory() {
        return category;
    }

    public void setCategory(FishermanCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "FishermanDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
                + ", address=" + address + ", city=" + city + ", category=" + category + "]";
    }
    
    
    
}
