package com.example.fishingmanagementbackend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fishingmanagementbackend.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation r WHERE fishing_spot_id = ?1 AND (r.arrival_date > CURRENT_TIMESTAMP OR r.departure_date > CURRENT_TIMESTAMP)", nativeQuery = true)
    List<Reservation> findFutureReservationsForSpot(Long spotId);
    
    @Query(value = "SELECT * FROM reservation r WHERE fisherman_id = ?1", nativeQuery = true)
    List<Reservation> findByFisherman(Long fishermanId);
    
    @Query(value = "SELECT * FROM reservation r WHERE fishing_spot_id = ?1", nativeQuery = true)
    List<Reservation> findByFishingSpot(Long fishingSpotId);
    
    @Query(value = "SELECT * FROM reservation r WHERE fishing_spot_id = ?1 AND (r.arrival_date > ?2 AND r.arrival_date < ?3)", nativeQuery = true)
    List<Reservation> findReservationsInInterval(Long fishingSpotId, LocalDate startDate, LocalDate endDate);
}
