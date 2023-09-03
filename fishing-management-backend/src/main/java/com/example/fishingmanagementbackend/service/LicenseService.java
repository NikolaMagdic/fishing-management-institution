package com.example.fishingmanagementbackend.service;

import java.security.Principal;
import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.LicenseDTO;
import com.example.fishingmanagementbackend.enumerations.LicenseType;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.License;
import com.example.fishingmanagementbackend.repository.FishermanRepository;
import com.example.fishingmanagementbackend.repository.LicenseRepository;
import com.example.fishingmanagementbackend.repository.UserRepository;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;
    
    @Autowired
    private FishermanRepository fishermanRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public License obtainLicense(LicenseDTO licenseDTO, Principal principal) {
        
        Long fishermanId = userRepository.findByUsername(principal.getName()).getFisherman().getId();
        
        List<License> existingValidLicenses = licenseRepository.getValidLicensesOfFisherman(fishermanId);
        
        // Ne moze se vaditi nova dozvola ukoliko postoji vazeca
        if(!existingValidLicenses.isEmpty()) {
            return null;
        } 
        
        License license;
        System.out.println(licenseDTO);
        if(licenseDTO.getType().equals(LicenseType.DAILY)) {
            license = new License(licenseDTO.getType(), licenseDTO.getDay(), null);
        } else {
            Year year = Year.now();
            license = new License(licenseDTO.getType(), null, year);
        }
        
        license.setConfirmed(false);
        //TODO: Uraditi proveru da se ne mogu izdati dve jednodnevne dozvole za isti datum
        Fisherman fisherman = this.fishermanRepository.getReferenceById(fishermanId);
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
    
    public List<License> getAllLicenseRequests() {
        List<License> notConfirmedYearlyLicenses;
        notConfirmedYearlyLicenses = licenseRepository.getNotConfirmedLicences();
        
        return notConfirmedYearlyLicenses;
    }
    
    public License confirmLicenseRequest(Long fishermanId) {
        
        List<License> licensesForConfirmation = licenseRepository.getNotConfirmedLicensesOfFisherman(fishermanId);
        // U jednom trenutku moze postojati samo jedan zahtev za godisnju dozvolu tako da bi ovo trebalo da radi
        License license = licensesForConfirmation.get(0);
        license.setConfirmed(true);
        
        return licenseRepository.save(license);
        
    }
}
