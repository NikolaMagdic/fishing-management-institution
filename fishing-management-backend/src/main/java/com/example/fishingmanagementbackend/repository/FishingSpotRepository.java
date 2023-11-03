package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fishingmanagementbackend.model.FishingSpot;
import com.example.fishingmanagementbackend.model.FishingSpotPK;

@Repository 
public interface FishingSpotRepository extends JpaRepository<FishingSpot, FishingSpotPK>{

    @Query(value = "SELECT * FROM fishing_spot WHERE fishing_area_id = ?1", nativeQuery = true)
    List<FishingSpot> findByFishingArea(Long id);
    
    //userRepository.findAll(Sort.by("LENGTH(name)"));
}
