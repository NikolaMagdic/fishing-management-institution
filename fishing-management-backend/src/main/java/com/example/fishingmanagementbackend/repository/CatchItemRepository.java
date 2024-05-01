package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.model.CatchItem;

public interface CatchItemRepository extends JpaRepository<CatchItem, Long>{

    @Query(value = "SELECT sum(quantity), sum(weight), fish_id, fishing_area_id "
            + " FROM catch_item ci LEFT OUTER JOIN catch c ON ci.catch_id = c.id"
            + " WHERE fisherman_id = ?1 AND YEAR(date) = ?2 AND ci.confirmation_status = 0"
            + " GROUP BY fish_id, fishing_area_id", nativeQuery = true)
    List<Object[]> findAllCatchItemsByFishermanInYear(Long fishermanId, int year);
 
    @Query(value = "SELECT sum(quantity), sum(weight), fish_id "
            + " FROM catch_item ci LEFT OUTER JOIN catch c ON ci.catch_id = c.id"
            + " WHERE fishing_area_id = ?1 AND YEAR(date) = ?2 AND ci.confirmation_status = 0"
            + " GROUP BY fish_id", nativeQuery = true)
    List<Object[]> findAllCatchItemsInFishingAreaForYear(Long areaId, int year);
    
}
