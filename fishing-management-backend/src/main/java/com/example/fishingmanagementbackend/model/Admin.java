package com.example.fishingmanagementbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Admin extends User {

    /**
     * 
     */
    private static final long serialVersionUID = -9074157111898057089L;

    public Admin() {}
    
}
