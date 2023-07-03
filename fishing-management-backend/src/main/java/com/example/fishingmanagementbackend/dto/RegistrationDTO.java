package com.example.fishingmanagementbackend.dto;

import java.util.Date;

import com.example.fishingmanagementbackend.enumerations.FishermanCategory;

public class RegistrationDTO {
    
    private String username;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    private Date dateOfBirth;
    
    private String address;
    
    private String city;
    
    private FishermanCategory category;
    
    public RegistrationDTO() {
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "RegistrationDTO [username=" + username + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", city=" + city
                + ", category=" + category + "]";
    }
    
}
