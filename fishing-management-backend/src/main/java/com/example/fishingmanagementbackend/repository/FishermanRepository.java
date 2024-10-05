package com.example.fishingmanagementbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.enumerations.FishermanCategory;
import com.example.fishingmanagementbackend.model.Fisherman;

public interface FishermanRepository extends JpaRepository<Fisherman, Long> {
    
    Page<Fisherman> findAll(Pageable pageable);
    
    Page<Fisherman> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(Pageable pageable, String firstName, String lastName);
    
    Page<Fisherman> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndCategory(Pageable pageable, String firstName, String lastName, FishermanCategory category);
    
    // 0 AS clazz_ zbog https://stackoverflow.com/questions/49804053/psqlexception-the-column-name-clazz-was-not-found-in-this-resultset
    @Query(value = "SELECT DISTINCT u.id, u.first_name, u.last_name, u.enabled, u.date_of_birth, u.username, u.password, u.last_password_reset_date, f.category, f.address, f.city, pf.registry_number, 1 AS clazz_ "
            + "FROM fisherman f LEFT OUTER JOIN catch c ON f.id = c.fisherman_id "
            + "LEFT OUTER JOIN fishing_area fa ON c.fishing_area_id = fa.id "
            + "LEFT OUTER JOIN keeping kee ON kee.fishing_area_id = fa.id "
            + "LEFT OUTER JOIN keeper k ON k.id = kee.keeper_id "
            + "LEFT OUTER JOIN catch_item ci ON ci.catch_id = c.id "
            + "LEFT OUTER JOIN app_user u ON f.id = u.id "
            + "LEFT OUTER JOIN professional_fisherman pf ON pf.id = u.id "
            + "WHERE k.id = ?1 AND ci.confirmation_status = 3", nativeQuery = true)
    Page<Fisherman> findAllFishermansWithNonConfirmedCatches(Pageable page, Long keeperId);
}
