package com.example.fishingmanagementbackend.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.KeeperDTO;
import com.example.fishingmanagementbackend.dto.KeeperRegistrationDTO;
import com.example.fishingmanagementbackend.exceptions.ForbiddenException;
import com.example.fishingmanagementbackend.model.Authority;
import com.example.fishingmanagementbackend.model.FishingArea;
import com.example.fishingmanagementbackend.model.Keeper;
import com.example.fishingmanagementbackend.model.User;
import com.example.fishingmanagementbackend.repository.FishingAreaRepository;
import com.example.fishingmanagementbackend.repository.KeeperRepository;
import com.example.fishingmanagementbackend.repository.UserRepository;

@Service
public class KeeperService {
    
    @Autowired
    private KeeperRepository keeperRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
    private FishingAreaRepository fishingAreaRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthorityService authService;
    
    @Autowired
    private EmailService emailService;
    
    public List<KeeperDTO> getAllKeepers() {
        List<Keeper> allKeepers = keeperRepository.findAll();
        List<KeeperDTO> allKeepersDTO = new ArrayList<>();
        for(Keeper k : allKeepers) {
            KeeperDTO keeperDTO = new KeeperDTO(k);
            allKeepersDTO.add(keeperDTO);
        }
        return allKeepersDTO;
    }
    
    public KeeperDTO getKeeperById(Long id, Principal principal) throws ForbiddenException {
        
        if(userService.findUserByUsername(principal.getName()).getAuthorities().iterator().next().getName().equals("ROLE_KEEPER")) {
            if(!userRepository.findByUsername(principal.getName()).getId().equals(id)) {
                throw new ForbiddenException("Nemate privilegije da vidite podatke ovog ribočuvara");
            }    
        }
        
        Keeper keeper = keeperRepository.getReferenceById(id);
        return new KeeperDTO(keeper);
        
    }
    
    /**Dodavanje novog ribocuvara u sistem*/
    public Keeper addNewKeeper(KeeperRegistrationDTO newKeeper) {
        User existingUser = userService.findUserByUsername(newKeeper.getUsername());
        if(existingUser != null) {
            return null;
        }
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        Keeper keeper = new Keeper();
        keeper.setUsername(newKeeper.getUsername());
        keeper.setPassword(encoder.encode(newKeeper.getPassword()));
        keeper.setEnabled(false);
        keeper.setFirstName(newKeeper.getFirstName());
        keeper.setLastName(newKeeper.getLastName());
        keeper.setDateOfBirth(newKeeper.getDateOfBirth());
        keeper.setLicenseNumber(newKeeper.getLicenseNumber());
        
        Set<Authority> authorities = authService.findByName("ROLE_KEEPER");
        keeper.setAuthorities(authorities);
        
        keeper = userRepository.save(keeper);
        
        try {
            emailService.sendMailAsync(keeper, newKeeper.getFirstName());
        } catch (MailException | InterruptedException e) {
            e.printStackTrace();
        }
        
        return keeper;
    }
    
    public Keeper updateKeeper(Long id, KeeperDTO keeperDTO, Principal principal) throws ForbiddenException {
        Keeper keeper = keeperRepository.getReferenceById(id);
        
        if(!userRepository.findByUsername(principal.getName()).getId().equals(id)) {
            throw new ForbiddenException("Nemate privilegije da menjate podatke ovog ribočuvara");
        }
        
        keeper.setFirstName(keeperDTO.getFirstName());
        keeper.setLastName(keeperDTO.getLastName());
        keeper.setDateOfBirth(keeperDTO.getDateOfBirth());
        keeper.setLicenseNumber(keeperDTO.getLicenseNumber());
        
        return keeperRepository.save(keeper);
    }
    
    public boolean addKeeperToFishingArea(Long keeperId, Long areaId) {

        Keeper keeper = keeperRepository.getReferenceById(keeperId);
        FishingArea fishingArea = fishingAreaRepository.getReferenceById(areaId);
        
        keeper.getFishingAreas().add(fishingArea);
        keeperRepository.save(keeper);
        
        return true;
        
    }
    
    public boolean removeKeeperFromFishingArea(Long keeperId, Long areaId) {
        Keeper keeper = keeperRepository.getReferenceById(keeperId);
        FishingArea fishingArea = fishingAreaRepository.getReferenceById(areaId);
        
        keeper.getFishingAreas().remove(fishingArea);
        keeperRepository.save(keeper);
        
        return true;
    }
    
}
