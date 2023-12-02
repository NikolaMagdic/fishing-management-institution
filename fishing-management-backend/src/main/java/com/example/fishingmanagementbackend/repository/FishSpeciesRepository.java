package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.model.FishSpecies;

public interface FishSpeciesRepository extends JpaRepository<FishSpecies, Long>{

    @Query(value = "SELECT DISTINCT * FROM fish_species fs LEFT OUTER JOIN containing c ON fs.id = c.fish_species_id WHERE fishing_area_id = ?1", nativeQuery = true)
    List<FishSpecies> findByFishingArea(Long areaId);
    
    // NOT IN je ekvivalentno MINUS u oracleSQL
    @Query(value = "SELECT * FROM fish_species fs WHERE fs.id NOT IN "
            + "(SELECT fish_species_id FROM fish_species fs LEFT OUTER JOIN containing c ON fs.id = c.fish_species_id WHERE fishing_area_id = ?1)", nativeQuery = true)
    List<FishSpecies> findNotInFishingArea(Long areaId);
    
    @Query(value = "SELECT * FROM fish_species fs WHERE fs.category != 2", nativeQuery = true)
    List<FishSpecies> findNativeFishSpecies();
}
