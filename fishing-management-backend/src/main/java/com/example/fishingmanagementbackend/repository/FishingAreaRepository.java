package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.model.FishingArea;

public interface FishingAreaRepository extends JpaRepository<FishingArea, Long>{

    @Query(value = "SELECT * FROM fishing_area fa LEFT OUTER JOIN keeping k ON fa.id = k.fishing_area_id WHERE keeper_id = ?1", nativeQuery = true)
    List<FishingArea> findKeptByKeeper(Long keeperId);
    
    @Query(value = "SELECT * FROM fishing_area fa WHERE fa.id NOT IN "
            + "(SELECT fishing_area_id FROM fishing_area fa LEFT OUTER JOIN keeping k ON fa.id = k.fishing_area_id WHERE keeper_id = ?1)", nativeQuery = true)
    List<FishingArea> findNotKeptByKeeper(Long keeperId);
    
    @Query(value = "SELECT * FROM fishing_area fa WHERE fa.parent_fishing_area_id = ?1", nativeQuery = true)
    List<FishingArea> findContainedFishingAreas(Long parentAreaId);
    
    @Query(value = "SELECT * FROM fishing_area fa WHERE fa.parent_fishing_area_id IS NULL", nativeQuery = true)
    List<FishingArea> findAllRootFishingAreas();
}
