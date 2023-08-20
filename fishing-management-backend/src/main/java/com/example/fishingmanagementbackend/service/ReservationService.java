package com.example.fishingmanagementbackend.service;

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

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private FishingSpotRepository fishingSpotRepository;
    
    @Autowired 
    private FishermanRepository fishermanRepository;

    public List<LocalDate> getFutureReservationsForFishingSpot(Long spotId) {
        List<Reservation> futureReservations = reservationRepository.findFutureReservationsForSpot(spotId);
        List<LocalDate> occupiedDates = new ArrayList<>();
        
        for(Reservation r: futureReservations) {
            occupiedDates.add(r.getArrivalDate());
        }
        return occupiedDates;
    }
    
    public Reservation createReservation(ReservationDTO reservationDTO) {
        // TODO: Ovo izmeniti da se uzme trenutno ulogovani korisnik
        reservationDTO.setFishermanId(1L);
        Reservation reservation = new Reservation(reservationDTO.getArrivalDate(), reservationDTO.getArrivalTime(),
                reservationDTO.getDepartureDate(), reservationDTO.getDepartureTime());
        
        FishingSpot fishingSpot = fishingSpotRepository.getReferenceById(reservationDTO.getFishingSpotId());
        Fisherman fisherman = fishermanRepository.getReferenceById(reservationDTO.getFishermanId());
        
        reservation.setFishingSpot(fishingSpot);
        reservation.setFisherman(fisherman);    
        
        return reservationRepository.save(reservation);
    }
}
