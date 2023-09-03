package com.example.fishingmanagementbackend.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.ReservationDTO;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.FishingSpot;
import com.example.fishingmanagementbackend.model.Reservation;
import com.example.fishingmanagementbackend.repository.FishermanRepository;
import com.example.fishingmanagementbackend.repository.FishingSpotRepository;
import com.example.fishingmanagementbackend.repository.ReservationRepository;
import com.example.fishingmanagementbackend.repository.UserRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private FishingSpotRepository fishingSpotRepository;
    
    @Autowired 
    private FishermanRepository fishermanRepository;
    
    @Autowired
    private UserRepository userRepository;

    public List<LocalDate> getFutureReservationsForFishingSpot(Long spotId) {
        List<Reservation> futureReservations = reservationRepository.findFutureReservationsForSpot(spotId);
        List<LocalDate> occupiedDates = new ArrayList<>();
        
        for(Reservation r: futureReservations) {
            occupiedDates.add(r.getArrivalDate());
        }
        return occupiedDates;
    }
    
    public Reservation createReservation(ReservationDTO reservationDTO, Principal principal) {
        
        Long fishermanId = userRepository.findByUsername(principal.getName()).getFisherman().getId();
        
        Reservation reservation = new Reservation(reservationDTO.getArrivalDate(), reservationDTO.getArrivalTime(),
                reservationDTO.getDepartureDate(), reservationDTO.getDepartureTime());
        
        FishingSpot fishingSpot = fishingSpotRepository.getReferenceById(reservationDTO.getFishingSpotId());
        Fisherman fisherman = fishermanRepository.getReferenceById(fishermanId);
        
        reservation.setFishingSpot(fishingSpot);
        reservation.setFisherman(fisherman);    
        
        return reservationRepository.save(reservation);
    }
}
