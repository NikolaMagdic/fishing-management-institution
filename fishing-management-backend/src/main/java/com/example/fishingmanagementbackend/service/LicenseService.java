package com.example.fishingmanagementbackend.service;

import java.security.Principal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.LicenseDTO;
import com.example.fishingmanagementbackend.enumerations.FishermanCategory;
import com.example.fishingmanagementbackend.enumerations.LicenseStatus;
import com.example.fishingmanagementbackend.enumerations.LicenseType;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.License;
import com.example.fishingmanagementbackend.model.ProfessionalFisherman;
import com.example.fishingmanagementbackend.model.RecreationalFisherman;
import com.example.fishingmanagementbackend.model.Reservation;
import com.example.fishingmanagementbackend.model.YearlyLicense;
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
    
    @Autowired
    private EmailService emailService;
    
    public YearlyLicense obtainYearLicense(LicenseDTO licenseDTO, Principal principal) {
        
        Long fishermanId = userRepository.findByUsername(principal.getName()).getId();
        
        List<YearlyLicense> existingValidLicenses = licenseRepository.getValidLicensesOfFisherman(fishermanId);
        
        // Ne moze se vaditi nova dozvola ukoliko postoji vazeca
        if(!existingValidLicenses.isEmpty()) {
            return null;
        } 
               
        Year year = Year.now();
        YearlyLicense license = new YearlyLicense(licenseDTO.getType(), year);
        
        license.setStatus(LicenseStatus.CREATED);

        Fisherman fisherman = this.fishermanRepository.getReferenceById(fishermanId);
        if(fisherman.getCategory().equals(FishermanCategory.RECREATIONAL)) {
            RecreationalFisherman f = (RecreationalFisherman) fisherman;
            license.setFisherman(f);
        } else {
            ProfessionalFisherman f = (ProfessionalFisherman) fisherman;
            license.setProfessionalFisherman(f);
            
        }
    
        licenseRepository.save(license);
        System.out.println(license);
        
        return license;
    }
    
    public License obtainDayLicense(LicenseDTO licenseDTO, Principal principal) {
    
        Long fishermanId = userRepository.findByUsername(principal.getName()).getId();
        
        List<License> licencesForThisDay = licenseRepository.getLicencesOfFishermanOnThisDay(fishermanId, licenseDTO.getDate());
        // Vec je izvadjena dozvola za taj dan
        if(!licencesForThisDay.isEmpty()) {
            return null;
        }
        
        License license = new License(licenseDTO.getType(), licenseDTO.getDate(), null);
        license.setStatus(LicenseStatus.CREATED);
        
        RecreationalFisherman fisherman;
        try {
            fisherman = (RecreationalFisherman) fishermanRepository.getReferenceById(fishermanId);
        } catch (ClassCastException ex) {
            return null;
        }
        
        license.setFisherman(fisherman);
        licenseRepository.save(license);
        
        return license;
    
    }
    
    public License obtainMultiDayLicense(LicenseDTO licenseDTO, Principal principal) throws Exception {
        
        Long fishermanId = userRepository.findByUsername(principal.getName()).getId();
        
        //TODO List<>
        if(licenseDTO.getEndDate().isAfter(licenseDTO.getDate().plusDays(7))) {
            throw new Exception("Višednevna dozvola ne može biti duža od 7 dana");
        }
        License license = new License(licenseDTO.getType(), licenseDTO.getDate(), licenseDTO.getEndDate());
        license.setStatus(LicenseStatus.CREATED);
        
        RecreationalFisherman fisherman;
        try {
            fisherman = (RecreationalFisherman) fishermanRepository.getReferenceById(fishermanId);    
        } catch (ClassCastException ex) {
            return null;
        }
        
        license.setFisherman(fisherman);
        licenseRepository.save(license);
        
        return license;
        
    }
    
    
    public YearlyLicense getExistingValidLicences(Principal principal) {
        
        Long fishermanId = userRepository.findByUsername(principal.getName()).getId();
        
        List<YearlyLicense> existingValidLicences = licenseRepository.getValidLicensesOfFisherman(fishermanId);
        // Moze biti samo jedna validna godisnja dozvola za ribolovca u jednom trenutku
        if(existingValidLicences.isEmpty()) {
            return null;
        }
        YearlyLicense license = existingValidLicences.get(0);
        
        return license;
    }
    
    public List<LicenseDTO> getAllDailyLicenses(Principal principal) {
        Long fishermanId = userRepository.findByUsername(principal.getName()).getId();
        List<License> dailyLicensesIfFisherman = licenseRepository.getDailyLicensesOfFisherman(fishermanId);
    
        List<LicenseDTO> licensesDTO = new ArrayList<>();
        for (License l : dailyLicensesIfFisherman) {
            LicenseDTO licenseDTO = new LicenseDTO(l);
            licensesDTO.add(licenseDTO);
        }
        
        return licensesDTO;
    }
    
    public List<LicenseDTO> getAllMultiDayLicenses(Principal principal) {
        Long fishermanId = userRepository.findByUsername(principal.getName()).getId();
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
        
        // sendEmailAboutConfirmation(license, true);
        
        return licenseRepository.save(license);
        
    }
    
    public License rejectLicenseRequest(Long licenseId) {
        
        License license = licenseRepository.getReferenceById(licenseId);   
        
     // Brisanje zakazanog termina ukoliko postoji
        if(!license.getType().equals(LicenseType.YEARLY)) {
            List<Reservation> reservations = reservationRepository.findByLicense(licenseId);
            Reservation reservation = reservations.get(0);
            reservationRepository.delete(reservation);
        }
        
        license.setStatus(LicenseStatus.REJECTED);
        
        //sendEmailAboutConfirmation(license, false);
        
        return licenseRepository.save(license);
    }
    
    public Boolean checkFishermansLicensesOnThisDay(Long fishermanId) {
        List<License> licenses = licenseRepository.findValidLicensesForToday(fishermanId);
        if(licenses.isEmpty()) {
            return false;
        }
        return true;
    }
    
    private void sendEmailAboutConfirmation(License license, boolean confirmed) {
        Fisherman fisherman;
        String time;
        if(license.getType().equals(LicenseType.YEARLY)) {
            license = (License) Hibernate.unproxy(license);
            YearlyLicense yearLicense = (YearlyLicense) license;
            time = " za " + yearLicense.getYear().toString() + ". godinu ";
            if(yearLicense.getProfessionalFisherman() != null) {
                fisherman = yearLicense.getProfessionalFisherman();
            } else {
                fisherman = yearLicense.getFisherman();
            }
        } else {
            fisherman = license.getFisherman();
            time = " za " + license.getDate().toString() + " "; 
        }
        try {
            emailService.sendMailAsyncForLicenseConfirmation(fisherman, fisherman.getFirstName() + " " + fisherman.getLastName(), confirmed, time);
        } catch (MailException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
