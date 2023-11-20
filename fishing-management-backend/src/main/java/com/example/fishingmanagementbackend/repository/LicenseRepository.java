package com.example.fishingmanagementbackend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.model.License;
import com.example.fishingmanagementbackend.model.YearlyLicense;

public interface LicenseRepository extends JpaRepository<License, Long> {

    @Query(value = "SELECT * FROM license l WHERE (l.fisherman_id = ?1 OR l.professional_fisherman_id = ?1) AND l.year = YEAR(CURRENT_TIMESTAMP) ", nativeQuery = true)
    List<YearlyLicense> getValidLicensesOfFisherman(Long fishermanId);
    
    @Query(value = "SELECT * FROM license l WHERE l.fisherman_id = ?1 AND l.date = ?2", nativeQuery = true)
    List<License> getLicencesOfFishermanOnThisDay(Long fishermanId, LocalDate date);
    
    @Query(value = "SELECT * FROM license l WHERE l.fisherman_id = ?1 AND l.type = 1", nativeQuery = true)
    List<License> getDailyLicensesOfFisherman(Long fishermanId);
    
    @Query(value = "SELECT * FROM license l WHERE l.fisherman_id = ?1 AND l.type = 2", nativeQuery = true)
    List<License> getMultiDayLicensesOfFisherman(Long fishermanId);
    
    @Query(value = "SELECT * FROM license l WHERE l.status = 2", nativeQuery = true)
    List<License> getNotConfirmedLicences();
    
    @Query(value = "SELECT * FROM license l WHERE l.status = 2 AND l.type = 0 AND l.fisherman_id = ?1", nativeQuery = true)
    List<License> getNotConfirmedLicensesOfFisherman(Long fishermanId);
    
    @Query(value = "SELECT * FROM license l WHERE l.status = 0 AND l.fisherman_id = ?1 "
            + "AND ((l.date = CURRENT_DATE()) OR (l.date <= CURRENT_DATE() AND l.end_date >= CURRENT_DATE()) OR (l.year = YEAR(CURRENT_TIMESTAMP)))", nativeQuery = true)
    List<License> findValidLicensesForToday(Long fishermanId);
}
