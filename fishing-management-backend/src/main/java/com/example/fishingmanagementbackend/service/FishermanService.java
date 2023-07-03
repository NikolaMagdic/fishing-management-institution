package com.example.fishingmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.FishermanDTO;
import com.example.fishingmanagementbackend.dto.RegistrationDTO;
import com.example.fishingmanagementbackend.model.Fisherman;
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
    
    public List<FishermanDTO> getAllFishermans() {
        List<FishermanDTO> allFishermanDTOs = new ArrayList<>();
        List<Fisherman> allFishermans = fishermanRepository.findAll();
        for(Fisherman f : allFishermans) {
            allFishermanDTOs.add(new FishermanDTO(f));
        }
        return allFishermanDTOs;
    }
    
    public FishermanDTO getFishermanById(Long id) {
        Fisherman fisherman = fishermanRepository.getReferenceById(id);
        return new FishermanDTO(fisherman);
    }
    
    /**Registrovanje novog ribolovca*/
    public Fisherman createNewFisherman(RegistrationDTO registrationDTO) {
        User existingUser = userService.findUserByUsername(registrationDTO.getUsername());
        if(existingUser != null) {
            return null;
        }
        
        Fisherman fisherman = new Fisherman(registrationDTO.getFirstName(),
                                            registrationDTO.getLastName(),
                                            registrationDTO.getDateOfBirth(),
                                            registrationDTO.getAddress(),
                                            registrationDTO.getCity(),
                                            registrationDTO.getCategory());
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(registrationDTO.getUsername(), encoder.encode(registrationDTO.getPassword()));
        fisherman.setUser(user);
        
        userRepository.save(user);
        return fishermanRepository.save(fisherman);  
    }
    
    public Fisherman updateFisherman(Long id, FishermanDTO fishermanDTO) {
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
