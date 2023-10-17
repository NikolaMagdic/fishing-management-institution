package com.example.fishingmanagementbackend.service;

import java.security.Principal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.LicenseDTO;
import com.example.fishingmanagementbackend.enumerations.LicenseStatus;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.License;
import com.example.fishingmanagementbackend.model.Reservation;
import com.example.fishingmanagementbackend.repository.FishermanRepository;
import com.example.fishingmanagementbackend.repository.LicenseRepository;
import com.example.fishingmanagementbackend.repository.ReservationRepository;
import com.example.fishingmanagementbackend.repository.UserRepository;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;
    
    @Autowired
    private FishermanRepository fishermanRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    public License obtainYearLicense(LicenseDTO licenseDTO, Principal principal) {
        
        Long fishermanId = userRepository.findByUsername(principal.getName()).getFisherman().getId();
        
        List<License> existingValidLicenses = licenseRepository.getValidLicensesOfFisherman(fishermanId);
        
        // Ne moze se vaditi nova dozvola ukoliko postoji vazeca
        if(!existingValidLicenses.isEmpty()) {
            return null;
        } 
               
        Year year = Year.now();
        License license = new License(licenseDTO.getType(), null, null, year);
        
        license.setStatus(LicenseStatus.CREATED);

        Fisherman fisherman = this.fishermanRepository.getReferenceById(fishermanId);
        license.setFisherman(fisherman);
        licenseRepository.save(license);
        
        return license;
    }
    
    public License obtainDayLicense(LicenseDTO licenseDTO, Principal principal) {
    
        Long fishermanId = userRepository.findByUsername(principal.getName()).getFisherman().getId();
        
        List<License> licencesForThisDay = licenseRepository.getLicencesOfFishermanOnThisDay(fishermanId, licenseDTO.getDate());
        // Vec je izvadjena dozvola za taj dan
        if(!licencesForThisDay.isEmpty()) {
            return null;
        }
        
        License license = new License(licenseDTO.getType(), licenseDTO.getDate(), null, null);
        license.setStatus(LicenseStatus.CREATED);
        license.setSpotId(licenseDTO.getSpotId());
        
        Fisherman fisherman = fishermanRepository.getReferenceById(fishermanId);
        license.setFisherman(fisherman);
        licenseRepository.save(license);
        
        return license;
    
    }
    
    public License obtainMultiDayLicense(LicenseDTO licenseDTO, Principal principal) throws Exception {
        
        Long fishermanId = userRepository.findByUsername(principal.getName()).getFisherman().getId();
        
        //TODO List<>
        if(licenseDTO.getEndDate().isAfter(licenseDTO.getDate().plusDays(7))) {
            throw new Exception("Višednevna dozvola ne može biti duža od 7 dana");
        }
        License license = new License(licenseDTO.getType(), licenseDTO.getDate(), licenseDTO.getEndDate(), null);
        license.setStatus(LicenseStatus.CREATED);
        license.setSpotId(licenseDTO.getSpotId());
        
        Fisherman fisherman = fishermanRepository.getReferenceById(fishermanId);
        license.setFisherman(fisherman);
        licenseRepository.save(license);
        
        return license;
        
    }
    
    
    public License getExistingValidLicences(Principal principal) {
        
        Long fishermanId = userRepository.findByUsername(principal.getName()).getFisherman().getId();
        
        List<License> existingValidLicences = licenseRepository.getValidLicensesOfFisherman(fishermanId);
        // Moze biti samo jedna validna dozvola za ribolovca u jednom trenutku
        if(existingValidLicences.isEmpty()) {
            return null;
        }
        License license = existingValidLicences.get(0);
        
        return license;
    }
    
    public List<LicenseDTO> getAllDailyLicenses(Principal principal) {
        Long fishermanId = userRepository.findByUsername(principal.getName()).getFisherman().getId();
        List<License> dailyLicensesIfFisherman = licenseRepository.getDailyLicensesOfFisherman(fishermanId);
    
        List<LicenseDTO> licensesDTO = new ArrayList<>();
        for (License l : dailyLicensesIfFisherman) {
            LicenseDTO licenseDTO = new LicenseDTO(l);
            licensesDTO.add(licenseDTO);
        }
        
        return licensesDTO;
    }
    
    public List<LicenseDTO> getAllMultiDayLicenses(Principal principal) {
        Long fishermanId = userRepository.findByUsername(principal.getName()).getFisherman().getId();
        List<License> multiDayLicensesOfFisherman = licenseRepository.getMultiDayLicensesOfFisherman(fishermanId);
        
        List<LicenseDTO> licensesDTO = new ArrayList<>();
        for(License l : multiDayLicensesOfFisherman) {
            LicenseDTO licenseDTO = new LicenseDTO(l);
            licensesDTO.add(licenseDTO);
        }
        
        return licensesDTO;
    }
    
    public List<License> getAllLicenseRequests() {
        List<License> notConfirmedYearlyLicenses;
        notConfirmedYearlyLicenses = licenseRepository.getNotConfirmedLicences();
        
        return notConfirmedYearlyLicenses;
    }
    
    public License confirmLicenseRequest(Long licenseId) {
        
        License license = licenseRepository.getReferenceById(licenseId);
        license.setStatus(LicenseStatus.CONFIRMED);
        
        return licenseRepository.save(license);
        
    }
    
    public License rejectLicenseRequest(Long licenseId) {
        
        License license = licenseRepository.getReferenceById(licenseId);
        
        // Brisanje zakazanog termina ukoliko postoji
        if(license.getSpotId() != 0) {
            List<Reservation> reservations = reservationRepository.findFutureReservationsForSpot(license.getSpotId());
            for(Reservation r : reservations) {
                if(r.getFisherman().getId() == license.getFisherman().getId())
                    reservationRepository.delete(r);
            }   
        }
        
        license.setStatus(LicenseStatus.REJECTED);
        
        
        
        return licenseRepository.save(license);
    }
}
