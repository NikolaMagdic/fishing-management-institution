package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.model.License;

public interface LicenseRepository extends JpaRepository<License, Long> {

    @Query(value = "SELECT * FROM license l WHERE l.fisherman_id = ?1 AND l.year = YEAR(CURRENT_TIMESTAMP) ", nativeQuery = true)
    List<License> getValidLicensesOfFisherman(Long fishermanId);
    
    @Query(value = "SELECT * FROM license l WHERE l.confirmed = false AND l.type = 0", nativeQuery = true)
    List<License> getNotConfirmedLicences();
    
    @Query(value = "SELECT * FROM license l WHERE l.confirmed = false AND l.type = 0 AND l.fisherman_id = ?1", nativeQuery = true)
    List<License> getNotConfirmedLicensesOfFisherman(Long fishermanId);
}
