package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fishingmanagementbackend.model.FishingArea;

@Repository
public interface FishingAreaRepository extends JpaRepository<FishingArea, Long>{

    
}
