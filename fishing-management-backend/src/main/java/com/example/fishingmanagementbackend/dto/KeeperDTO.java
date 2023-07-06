package com.example.fishingmanagementbackend.dto;

import java.util.Date;

import com.example.fishingmanagementbackend.model.Keeper;

public class KeeperDTO {

    private String firstName;
    
    private String lastName;
    
    private Date dateOfBirth;
    
    public KeeperDTO() {}
    
    public KeeperDTO(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    
    public KeeperDTO(Keeper keeper) {
        this.firstName = keeper.getFirstName();
        this.lastName = keeper.getLastName();
        this.dateOfBirth = keeper.getDateOfBirth();
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

    @Override
    public String toString() {
        return "KeeperDTO [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + "]";
    }
    
    
}
