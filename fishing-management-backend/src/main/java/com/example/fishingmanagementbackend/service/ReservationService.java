package com.example.fishingmanagementbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.ReservationDTO;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.FishingSpot;
import com.example.fishingmanagementbackend.model.Reservation;
import com.example.fishingmanagementbackend.repository.FishermanRepository;
import com.example.fishingmanagementbackend.repository.FishingSpotRepository;
import com.example.fishingmanagementbackend.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private FishingSpotRepository fishingSpotRepository;
    
    @Autowired 
    private FishermanRepository fishermanRepository;

    public Reservation createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation(reservationDTO.getArrivalDate(), reservationDTO.getArrivalTime(),
                reservationDTO.getDepartureDate(), reservationDTO.getDepartureTime());
        
        FishingSpot fishingSpot = fishingSpotRepository.getReferenceById(reservationDTO.getFishingSpotId());
        Fisherman fisherman = fishermanRepository.getReferenceById(reservationDTO.getFishermanId());
        
        reservation.setFishingSpot(fishingSpot);
        reservation.setFisherman(fisherman);    
        
        return reservationRepository.save(reservation);
    }
}
