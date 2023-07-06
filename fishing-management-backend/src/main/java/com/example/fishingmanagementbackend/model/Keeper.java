package com.example.fishingmanagementbackend.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Keeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private Date dateOfBirth;
    
    @OneToOne()
    private User user;
    
    @ManyToMany
    @JoinTable(name = "keeps",
               joinColumns = @JoinColumn(name = "fishing_area_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "keeper_id", referencedColumnName = "id"))
    private Set<FishingArea> fishingAreas = new HashSet<>();
    
    public Keeper() {}
    
    public Keeper(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Set<FishingArea> getFishingAreas() {
        return fishingAreas;
    }

    public void setFishingAreas(Set<FishingArea> fishingAreas) {
        this.fishingAreas = fishingAreas;
    }

    @Override
    public String toString() {
        return "Keeper [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
                + dateOfBirth + ", user=" + user + "]";
    }
    
    
}
