package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fishingmanagementbackend.model.Fisherman;

@Repository
public interface FishermanRepository extends JpaRepository<Fisherman, Long>{

    @Query(value = "SELECT * FROM fisherman f LEFT OUTER JOIN license l ON f.id = l.fisherman_id WHERE confirmed = 0", nativeQuery = true)
    List<Fisherman> findAllFishermansWithLicenseRequests();
}
