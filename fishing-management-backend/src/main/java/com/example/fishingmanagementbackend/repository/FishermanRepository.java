package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fishingmanagementbackend.model.Fisherman;

@Repository
public interface FishermanRepository extends JpaRepository<Fisherman, Long>{
    
    @Query(value = "SELECT DISTINCT * "
            + "FROM fisherman f LEFT OUTER JOIN catch c ON f.id = c.fisherman_id "
            + "LEFT OUTER JOIN fishing_area fa ON c.fishing_area_id = fa.id "
            + "LEFT OUTER JOIN keeps kee ON kee.fishing_area_id = fa.id "
            + "LEFT OUTER JOIN keeper k ON k.id = kee.keeper_id "
            + "LEFT OUTER JOIN catch_item ci ON ci.catch_id = c.id "
            + "LEFT OUTER JOIN app_user u ON f.id = u.id "
            + "WHERE k.id = ?1 AND ci.confirmation_status = 2", nativeQuery = true)
    List<Fisherman> findAllFishermansWithNonConfirmedCatches(Long keeperId);
}
