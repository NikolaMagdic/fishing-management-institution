package com.example.fishingmanagementbackend.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.FishermanDTO;
import com.example.fishingmanagementbackend.dto.RegistrationDTO;
import com.example.fishingmanagementbackend.exceptions.ForbiddenException;
import com.example.fishingmanagementbackend.model.Authority;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.Keeper;
import com.example.fishingmanagementbackend.model.User;
import com.example.fishingmanagementbackend.repository.FishermanRepository;
import com.example.fishingmanagementbackend.repository.UserRepository;

@Service
public class FishermanService {

    @Autowired
    private FishermanRepository fishermanRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthorityService authService;
    
    @Autowired
    private EmailService emailService;
    
    public List<FishermanDTO> getAllFishermans() {
        List<FishermanDTO> allFishermanDTOs = new ArrayList<>();
        List<Fisherman> allFishermans = fishermanRepository.findAll();
        for(Fisherman f : allFishermans) {
            allFishermanDTOs.add(new FishermanDTO(f));
        }
        return allFishermanDTOs;
    }

    
    /** Vraca sve ribolovce sa nepotvrdjenim ulovima za prikaz ribocuvaru 
     * koji je nadlezan za ribolovnu vodu na kojoj su ti ulovi ostvareni*/
    public List<FishermanDTO> getAllFishermansWithNonConfirmedCatches(Principal principal) {
        Keeper loggedKeeper = userRepository.findByUsername(principal.getName()).getKeeper();
        
        List<Fisherman> fishermansWithNonConfirmedCatches = fishermanRepository.findAllFishermansWithNonConfirmedCatches(loggedKeeper.getId());
        List<FishermanDTO> fishermansDTO = new ArrayList<>();
        
        for(Fisherman f : fishermansWithNonConfirmedCatches) {
            fishermansDTO.add(new FishermanDTO(f));
        }
        
        return fishermansDTO;
    }
    
    public FishermanDTO getFishermanById(Long id, Principal principal) {
        
        // Branim ribolovcima da vide i menjaju podatke drugih ribolovaca
        if(userRepository.findByUsername(principal.getName()).getFisherman() != null) {
            if(!userRepository.findByUsername(principal.getName()).getFisherman().getId().equals(id)) {
                throw new ForbiddenException("Nemate privilegije da vidite podatke ovog ribolovca");
            }    
        }
        
        Fisherman fisherman = fishermanRepository.getReferenceById(id);
        return new FishermanDTO(fisherman);
    }
    
    /**Registrovanje novog ribolovca*/
    public Fisherman createNewFisherman(RegistrationDTO registrationDTO) {
        User existingUser = userService.findUserByUsername(registrationDTO.getUsername());
        if(existingUser != null) {
            return null;
        }
        // TODO: Ovde bi trebalo vratiti bad request
        if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            return null;
        }
        
        Fisherman fisherman = new Fisherman(registrationDTO.getFirstName(),
                                            registrationDTO.getLastName(),
                                            registrationDTO.getDateOfBirth(),
                                            registrationDTO.getAddress(),
                                            registrationDTO.getCity(),
                                            registrationDTO.getCategory());
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // Nalog ribolovca nije enabled dok ga ne verifikuje preko mejla
        User user = new User(registrationDTO.getUsername(), encoder.encode(registrationDTO.getPassword()), false);
        
        // Dodela role odnosno privilegija
        Set<Authority> authorities = authService.findByName("ROLE_FISHERMAN");
        user.setAuthorities(authorities);
        
        fisherman.setUser(user);
        
        userRepository.save(user);
        // Saljemo mejl sa linkom za potvrdu registracije
        try {
            emailService.sendMailAsync(user, registrationDTO.getFirstName());
        } catch (MailException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return fishermanRepository.save(fisherman);  
    }
    
    public Fisherman updateFisherman(Long id, FishermanDTO fishermanDTO, Principal principal) throws ForbiddenException {
        
        if(!userRepository.findByUsername(principal.getName()).getFisherman().getId().equals(id)) {
            throw new ForbiddenException("Nemate privilegije da menjate podatke ovog ribolovca");
        }
            
        Fisherman fisherman = fishermanRepository.getReferenceById(id);
        fisherman.setFirstName(fishermanDTO.getFirstName());
        fisherman.setLastName(fishermanDTO.getLastName());
        fisherman.setDateOfBirth(fishermanDTO.getDateOfBirth());
        fisherman.setAddress(fishermanDTO.getAddress());
        fisherman.setCity(fishermanDTO.getCity());
        fisherman.setCategory(fishermanDTO.getCategory());
        
        return fishermanRepository.save(fisherman);
    }
    
}
