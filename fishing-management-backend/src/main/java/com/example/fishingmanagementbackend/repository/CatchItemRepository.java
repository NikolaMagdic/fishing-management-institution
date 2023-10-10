package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.model.CatchItem;

public interface CatchItemRepository extends JpaRepository<CatchItem, Long>{

    @Query(value = "SELECT sum(quantity), sum(weight), fish_id, fishing_area_id "
            + " FROM catch_item ci LEFT OUTER JOIN catch c ON ci.catch_id = c.id"
            + " WHERE fisherman_id = ?1 AND YEAR(time) = ?2"
            + " GROUP BY fish_id, fishing_area_id", nativeQuery = true)
    List<Object[]> findAllCatchItemsByFishermanInYear(Long fishermanId, int year);
    
}