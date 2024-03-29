package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

public class KeeperRegistrationDTO {

    private String username;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    private LocalDate dateOfBirth;
    
    private String licenseNumber;
    
    public KeeperRegistrationDTO() {}

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "KeeperRegistrationDTO [username=" + username + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", licenseNumber=" + licenseNumber + "]";
    }
    
}
