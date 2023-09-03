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
    
    @GetMapping("/requests")
    public ResponseEntity<List<LicenseDTO>> getAllLicenseRequests() {
        List<License> notConfirmedValidLicenses = licenseService.getAllLicenseRequests(); 
        List<LicenseDTO> notConfirmedValidLicensesDTO = new ArrayList<>();
        
        for(License license: notConfirmedValidLicenses) {
            LicenseDTO licenseDTO = new LicenseDTO(license);
            notConfirmedValidLicensesDTO.add(licenseDTO);
        }
        return ResponseEntity.ok(notConfirmedValidLicensesDTO);
    }
    
    @PostMapping
    public ResponseEntity<LicenseDTO> obtainLicense(@RequestBody LicenseDTO licenseDTO, Principal principal) {
        
        License newLicense = licenseService.obtainLicense(licenseDTO, principal);
        if(newLicense == null) 
            return ResponseEntity.badRequest().build();
        
        return ResponseEntity.ok(new LicenseDTO(newLicense));
        
    }
    
    @PatchMapping("/{fishermanId}")
    public ResponseEntity<Boolean> confirmLicenseRequest(@PathVariable("fishermanId") Long fishermanId) {
        licenseService.confirmLicenseRequest(fishermanId);
        return ResponseEntity.ok(true);
    }
}
