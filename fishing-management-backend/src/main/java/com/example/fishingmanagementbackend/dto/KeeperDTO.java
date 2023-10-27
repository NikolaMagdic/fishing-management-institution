package com.example.fishingmanagementbackend.dto;

import java.time.LocalDate;

import com.example.fishingmanagementbackend.model.Keeper;

public class KeeperDTO {
    
    private Long id;

    private String firstName;
    
    private String lastName;
    
    private LocalDate dateOfBirth;
    
    public KeeperDTO() {}
    
    public KeeperDTO(Long id, String firstName, String lastName, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    
    public KeeperDTO(Keeper keeper) {
        this.id = keeper.getId();
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "KeeperDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + "]";
    }
    
    
}
