package com.example.fishingmanagementbackend.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.ReservationDTO;
import com.example.fishingmanagementbackend.dto.ReservationResponseDTO;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.FishingSpot;
import com.example.fishingmanagementbackend.model.FishingSpotPK;
import com.example.fishingmanagementbackend.model.License;
import com.example.fishingmanagementbackend.model.Reservation;
import com.example.fishingmanagementbackend.repository.FishermanRepository;
import com.example.fishingmanagementbackend.repository.FishingSpotRepository;
import com.example.fishingmanagementbackend.repository.LicenseRepository;
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
    
    @Autowired 
    private LicenseRepository licenseRepository;

    public List<LocalDate> getFutureReservationsForFishingSpot(Long spotId, Long areaId) {
        List<Reservation> futureReservations = reservationRepository.findFutureReservationsForSpot(spotId, areaId);
        List<LocalDate> occupiedDates = new ArrayList<>();
        
        for(Reservation r: futureReservations) {
            if(r.getDepartureDate() != null) {
                // Visednevni termin
                System.out.println("USAO U IF");
                List<LocalDate> datesBetWeen = findAllDatesBetween(r.getArrivalDate(), r.getDepartureDate());
                occupiedDates.addAll(datesBetWeen);
            } else {
                // Jednodnevni termin
                System.out.println("USAO U ELSE");
                occupiedDates.add(r.getArrivalDate());
            }
        }
        return occupiedDates;
    }
    
    public List<ReservationResponseDTO> getAllReservationsOfFisherman(Long fishermanId) {
        List<Reservation> reservations = reservationRepository.findByFisherman(fishermanId);
        List<ReservationResponseDTO> reservationsDTO = new ArrayList<>();
        
        for(Reservation r : reservations) {
            ReservationResponseDTO reservationDTO = new ReservationResponseDTO(r);
            reservationsDTO.add(reservationDTO);
        }
        return reservationsDTO;
    }
    
    public List<ReservationResponseDTO> getAllReservationsForFishingSpot(Long fishingSpotId, Long fishingAreaId) {
        List<Reservation> reservations = reservationRepository.findByFishingSpot(fishingSpotId, fishingAreaId);
        List<ReservationResponseDTO> reservationsDTO = new ArrayList<>();
        
        for(Reservation r : reservations) {
            ReservationResponseDTO reservationDTO = new ReservationResponseDTO(r);
            reservationsDTO.add(reservationDTO);
        }
        return reservationsDTO;
    }
    
    public Reservation createReservation(ReservationDTO reservationDTO, Principal principal) throws Exception {
        
        Long fishermanId = userRepository.findByUsername(principal.getName()).getId();
        
        System.out.println(reservationDTO);
        if(reservationDTO.getDepartureDate() != null) {
            boolean validRequest = checkIfDatesAreValid(reservationDTO.getFishingSpotId(), reservationDTO.getFishingAreaId(), reservationDTO.getArrivalDate(), reservationDTO.getDepartureDate());
            System.out.println(validRequest);
            if(!validRequest) 
                throw new Exception("Datumi nisu ispravni");
        }
        
        Reservation reservation = new Reservation(reservationDTO.getArrivalDate(),
                reservationDTO.getDepartureDate());
        
        FishingSpotPK id = new FishingSpotPK(reservationDTO.getFishingSpotId(), reservationDTO.getFishingAreaId());
        FishingSpot fishingSpot = fishingSpotRepository.getReferenceById(id);
        Fisherman fisherman = fishermanRepository.getReferenceById(fishermanId);
        
        reservation.setFishingSpot(fishingSpot);
        reservation.setFisherman(fisherman);  
        
        // Ukoliko je izvadjena dozvola uz rezervaciju
        if(reservationDTO.getLicenseId() != null) {
            License license = licenseRepository.getReferenceById(reservationDTO.getLicenseId());
            reservation.setLicense(license);
        }
        
        return reservationRepository.save(reservation);
    }
    
    /**Funkcija koja vraca sve datume izmedju dva prosledjena datuma
     * @param startDate: pocetni datum
     * @param endDate: krajnji datum
     * @return Lista datuma izmedju pocetnog i krajnjeg datuma ukljucujuci i njih*/
    private List<LocalDate> findAllDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = startDate.datesUntil(endDate.plusDays(1)).toList();
        return dates;
    }
    
    // Provera da slucajno uneseni datumi ne obuhvataju vec neki termin
    private boolean checkIfDatesAreValid(Long fishingSpotId, Long fishingAreaId, LocalDate startDate, LocalDate endDate) {
        System.out.println("OVDE SAM");
        List<Reservation> conflictResevations = reservationRepository.findReservationsInInterval(fishingSpotId, fishingAreaId, startDate, endDate);

        if(conflictResevations.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
}
