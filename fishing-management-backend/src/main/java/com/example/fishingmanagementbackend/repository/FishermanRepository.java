package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fishingmanagementbackend.model.Fisherman;

@Repository
public interface FishermanRepository extends JpaRepository<Fisherman, Long>{
    
    @Query(value = "SELECT DISTINCT f.id, f.first_name, f.last_name, f.date_of_birth, f.address, f.city, f.category, f.user_id "
            + "FROM fisherman f LEFT OUTER JOIN catch c ON f.id = c.fisherman_id "
            + "LEFT OUTER JOIN fishing_area fa ON c.fishing_area_id = fa.id "
            + "LEFT OUTER JOIN keeps kee ON kee.fishing_area_id = fa.id "
            + "LEFT OUTER JOIN keeper k ON k.id = kee.keeper_id "
            + "LEFT OUTER JOIN catch_item ci ON ci.catch_id = c.id "
            + "WHERE k.id = ?1 AND ci.confirmed = false", nativeQuery = true)
    List<Fisherman> findAllFishermansWithNonConfirmedCatches(Long keeperId);
}
