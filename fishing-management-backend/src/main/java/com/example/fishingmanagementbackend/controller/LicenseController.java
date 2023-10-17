package com.example.fishingmanagementbackend.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fishingmanagementbackend.dto.LicenseDTO;
import com.example.fishingmanagementbackend.dto.LicenseRequestDTO;
import com.example.fishingmanagementbackend.enumerations.LicenseType;
import com.example.fishingmanagementbackend.model.License;
import com.example.fishingmanagementbackend.service.LicenseService;

@RestController
@RequestMapping("api/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;
    
    @GetMapping
    public ResponseEntity<LicenseDTO> getExistingValidLicence(Principal principal) {
        System.out.println(principal);
        
        License license = licenseService.getExistingValidLicences(principal);
        if(license == null) {
            return ResponseEntity.ok(null);
        }
        LicenseDTO licenseDTO = new LicenseDTO(license);
        
        return ResponseEntity.ok(licenseDTO);
    }
    
    @GetMapping("/daily")
    public ResponseEntity<List<LicenseDTO>> getExistingDailyLicensesOfFisherman(Principal principal) {
        List<LicenseDTO> allDailyLicensesOfFisherman = licenseService.getAllDailyLicenses(principal);
        return ResponseEntity.ok(allDailyLicensesOfFisherman);
    }
    
    @GetMapping("/multiday")
    public ResponseEntity<List<LicenseDTO>> getExistingMultiDayLicensesOfFisherman(Principal principal) {
        List<LicenseDTO> allMultiDayLicensesOfFisherman;
        try {
            allMultiDayLicensesOfFisherman = licenseService.getAllMultiDayLicenses(principal);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(allMultiDayLicensesOfFisherman);
    }
    
    @GetMapping("/requests")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<LicenseRequestDTO>> getAllLicenseRequests() {
        List<License> notConfirmedValidLicenses = licenseService.getAllLicenseRequests(); 
        List<LicenseRequestDTO> notConfirmedValidLicensesDTO = new ArrayList<>();
        
        for(License license: notConfirmedValidLicenses) {
            LicenseRequestDTO licenseRequestDTO = new LicenseRequestDTO(license);
            notConfirmedValidLicensesDTO.add(licenseRequestDTO);
        }
        return ResponseEntity.ok(notConfirmedValidLicensesDTO);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('FISHERMAN')")
    public ResponseEntity<LicenseDTO> obtainLicense(@RequestBody LicenseDTO licenseDTO, Principal principal) throws Exception {
        
        License newLicense;
        if(licenseDTO.getType() == LicenseType.DAILY) {
            newLicense = licenseService.obtainDayLicense(licenseDTO, principal);
        } else if (licenseDTO.getType() == LicenseType.YEARLY) {
            newLicense = licenseService.obtainYearLicense(licenseDTO, principal);
        } else {
            newLicense = licenseService.obtainMultiDayLicense(licenseDTO, principal);
        }
        
        if(newLicense == null) 
            return ResponseEntity.badRequest().build();
        
        return ResponseEntity.ok(new LicenseDTO(newLicense));
        
    }
    
    @PatchMapping("/{licenseId}/confirm")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> confirmLicenseRequest(@PathVariable("licenseId") Long licenseId) {
        licenseService.confirmLicenseRequest(licenseId);
        return ResponseEntity.ok(true);
    }
    
    @PatchMapping("/{licenseId}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> rejectLicenseRequest(@PathVariable("licenseId") Long licenseId) {
        licenseService.rejectLicenseRequest(licenseId);
        return ResponseEntity.ok(true);
    }
}
