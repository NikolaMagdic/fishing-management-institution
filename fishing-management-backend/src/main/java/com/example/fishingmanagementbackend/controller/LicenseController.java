package com.example.fishingmanagementbackend.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    
    @GetMapping("/requests")
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
    public ResponseEntity<LicenseDTO> obtainLicense(@RequestBody LicenseDTO licenseDTO, Principal principal) {
        
        License newLicense;
        if(licenseDTO.getType() == LicenseType.DAILY) {
            newLicense = licenseService.obtainDayLicense(licenseDTO, principal);
        } else {
            newLicense = licenseService.obtainYearLicense(licenseDTO, principal);
        }
        
        if(newLicense == null) 
            return ResponseEntity.badRequest().build();
        
        return ResponseEntity.ok(new LicenseDTO(newLicense));
        
    }
    
    @PatchMapping("/{licenseId}/confirm")
    public ResponseEntity<Boolean> confirmLicenseRequest(@PathVariable("licenseId") Long licenseId) {
        licenseService.confirmLicenseRequest(licenseId);
        return ResponseEntity.ok(true);
    }
    
    @PatchMapping("/{licenseId}/reject")
    public ResponseEntity<Boolean> rejectLicenseRequest(@PathVariable("licenseId") Long licenseId) {
        licenseService.rejectLicenseRequest(licenseId);
        return ResponseEntity.ok(true);
    }
}
