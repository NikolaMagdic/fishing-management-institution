package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

import com.example.fishingmanagementbackend.enumerations.FishermanCategory;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.ProfessionalFisherman;

public class FishermanDTO {
    
    private Long id;

    private String firstName;
    
    private String lastName;
    
    private LocalDate dateOfBirth;
    
    private String address;
    
    private String city;
    
    private FishermanCategory category;
    
    private Integer registryNumber;
    
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
    
    public FishermanDTO(ProfessionalFisherman fisherman) {
        this.id = fisherman.getId();
        this.firstName = fisherman.getFirstName();
        this.lastName = fisherman.getLastName();
        this.dateOfBirth = fisherman.getDateOfBirth();
        this.address = fisherman.getAddress();
        this.city = fisherman.getCity();
        this.category = fisherman.getCategory();
        this.registryNumber = fisherman.getRegistryNumber();
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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

    public Integer getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(Integer registryNumber) {
        this.registryNumber = registryNumber;
    }

    @Override
    public String toString() {
        return "FishermanDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
                + dateOfBirth + ", address=" + address + ", city=" + city + ", category=" + category
                + ", registryNumber=" + registryNumber + "]";
    }

    
}
