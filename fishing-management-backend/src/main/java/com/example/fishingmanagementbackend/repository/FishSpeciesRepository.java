package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fishingmanagementbackend.model.FishSpecies;

@Repository
public interface FishSpeciesRepository extends JpaRepository<FishSpecies, Long>{

    @Query(value = "SELECT DISTINCT * FROM fish_species fs JOIN containing c WHERE fishing_area_id = ?1", nativeQuery = true)
    List<FishSpecies> findByFishingArea(Long areaId);
    
    @Query(value = "SELECT DISTINCT * FROM fish_species fs LEFT OUTER JOIN containing c ON fs.id = c.fish_species_id WHERE fishing_area_id != ?1 or fish_species_id IS NULL", nativeQuery = true)
    List<FishSpecies> findNotInFishingArea(Long areaId);
}