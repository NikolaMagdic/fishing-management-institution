package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fishingmanagementbackend.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    
    Authority findByName(String name);
    
}
