package com.example.fishingmanagementbackend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation r WHERE fishing_spot_id = ?1 AND fishing_area_id = ?2 AND (r.arrival_date > CURRENT_TIMESTAMP OR r.departure_date > CURRENT_TIMESTAMP) AND cancelled = false", nativeQuery = true)
    List<Reservation> findFutureReservationsForSpot(Long spotId, Long areaId);
    
    @Query(value = "SELECT * FROM reservation r WHERE fisherman_id = ?1", nativeQuery = true)
    List<Reservation> findByFisherman(Long fishermanId);
    
    @Query(value = "SELECT * FROM reservation r WHERE fishing_spot_id = ?1 AND fishing_area_id = ?2", nativeQuery = true)
    List<Reservation> findByFishingSpot(Long fishingSpotId, Long areaId);
    
    @Query(value = "SELECT * FROM reservation r WHERE fishing_spot_id = ?1 AND fishing_area_id = ?2 AND (r.arrival_date > ?3 AND r.arrival_date < ?4) AND cancelled = false", nativeQuery = true)
    List<Reservation> findReservationsInInterval(Long fishingSpotId, Long fishingAreaId, LocalDate startDate, LocalDate endDate);
    
    @Query(value = "SELECT * FROM reservation r WHERE license_id = ?1", nativeQuery = true)
    List<Reservation> findByLicense(Long licenseId);
    
    @Query(value = "SELECT * FROM reservation r WHERE fisherman_id = ?1 AND (r.arrival_date > CURRENT_TIMESTAMP OR r.departure_date > CURRENT_TIMESTAMP) AND cancelled = false", nativeQuery = true)
    List<Reservation> findFutureByFisherman(Long fishermanId);

}