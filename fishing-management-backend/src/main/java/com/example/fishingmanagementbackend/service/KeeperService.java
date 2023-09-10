package com.example.fishingmanagementbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.KeeperDTO;
import com.example.fishingmanagementbackend.dto.KeeperRegistrationDTO;
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
    
    public List<KeeperDTO> getAllKeepers() {
        List<Keeper> allKeepers = keeperRepository.findAll();
        List<KeeperDTO> allKeepersDTO = new ArrayList<>();
        for(Keeper k : allKeepers) {
            KeeperDTO keeperDTO = new KeeperDTO(k);
            allKeepersDTO.add(keeperDTO);
        }
        return allKeepersDTO;
    }
    
    public KeeperDTO getKeeperById(Long id) {
        
        Keeper keeper = keeperRepository.getReferenceById(id);
        return new KeeperDTO(keeper);
        
    }
    
    /**Dodavanje novog ribocuvara u sistem*/
    public Keeper addNewKeeper(KeeperRegistrationDTO newKeeper) {
        User existingUser = userService.findUserByUsername(newKeeper.getUsername());
        if(existingUser != null) {
            return null;
        }

        Keeper keeper = new Keeper(newKeeper.getFirstName(),
                                 newKeeper.getLastName(),
                                 newKeeper.getDateOfBirth());
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(newKeeper.getUsername(), encoder.encode(newKeeper.getPassword()), true);
        
        Set<Authority> authorities = authService.findByName("ROLE_KEEPER");
        user.setAuthorities(authorities);
        keeper.setUser(user);
        
        userRepository.save(user);
        return keeperRepository.save(keeper);
    }
    
    public Keeper updateKeeper(Long id, KeeperDTO keeperDTO) {
        Keeper keeper = keeperRepository.getReferenceById(id);
        
        keeper.setFirstName(keeperDTO.getFirstName());
        keeper.setLastName(keeperDTO.getLastName());
        keeper.setDateOfBirth(keeperDTO.getDateOfBirth());
        
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
