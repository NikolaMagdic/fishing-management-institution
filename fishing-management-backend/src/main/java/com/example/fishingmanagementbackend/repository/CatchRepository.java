package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.model.Catch;
import com.example.fishingmanagementbackend.model.Fisherman;


public interface CatchRepository extends JpaRepository<Catch, Long>{

    List<Catch> findByFisherman(Fisherman fisherman);
    
    @Query(value = "SELECT * FROM catch c LEFT OUTER JOIN catch_item ci ON c.id = ci.catch_id WHERE ci.item_id = ?1", nativeQuery = true)
    Catch findByCatchItemId(Long catcItemId);
    
}
