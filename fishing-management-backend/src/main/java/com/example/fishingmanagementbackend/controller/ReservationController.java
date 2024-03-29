package com.example.fishingmanagementbackend.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fishingmanagementbackend.dto.ReservationDTO;
import com.example.fishingmanagementbackend.dto.ReservationResponseDTO;
import com.example.fishingmanagementbackend.model.Reservation;
import com.example.fishingmanagementbackend.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    
    @GetMapping("/spot/{spotId}/area/{areaId}/future")
    public ResponseEntity<List<LocalDate>> getFutureReservationsForFishingSpot(@PathVariable("spotId") Long spotId, @PathVariable("areaId") Long areaId) {
        List<LocalDate> futureReservations = reservationService.getFutureReservationsForFishingSpot(spotId, areaId);
        System.out.println(futureReservations);
        return ResponseEntity.status(200).body(futureReservations);
    }
    
    @GetMapping("/fisherman/{fishermanId}")
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservationsOfFisherman(@PathVariable("fishermanId") Long fishermanId) {
        List<ReservationResponseDTO> reservations = reservationService.getAllReservationsOfFisherman(fishermanId);
        return ResponseEntity.ok(reservations);
    }
    
    @GetMapping("/spot/{spotId}/area/{areaId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'KEEPER')")
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservationsForFishingSpot(@PathVariable("spotId") Long spotId, @PathVariable("areaId") Long areaId) {
        List<ReservationResponseDTO> reservations = reservationService.getAllReservationsForFishingSpot(spotId, areaId);
        return ResponseEntity.ok(reservations);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('FISHERMAN')")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO, Principal principal) {
        
        if(principal == null) {
            return ResponseEntity.status(401).build();
        }
        
        Reservation reservation;
        
        try {
            reservation = reservationService.createReservation(reservationDTO, principal);    
        } catch (Exception ex) {
            return ResponseEntity.status(400).build();
        }
        
        return ResponseEntity.status(201).body(new ReservationDTO(reservation));
    }
    
    @DeleteMapping("/{reservationId}")
    @PreAuthorize("hasRole('FISHERMAN')")
    public ResponseEntity<?> cancelReservation(@PathVariable("reservationId") Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }
}
