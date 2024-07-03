package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.model.Catch;
import com.example.fishingmanagementbackend.model.Fisherman;


public interface CatchRepository extends JpaRepository<Catch, Long>{

    List<Catch> findByFisherman(Fisherman fisherman);
    
    @Query(value = "SELECT * FROM catch c WHERE fisherman_id = ?1 AND YEAR(date) = ?2", nativeQuery = true)
    List<Catch> findAllCatchesByFishermanForYear(Long fishermanId, int year);
}
