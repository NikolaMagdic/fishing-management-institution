package com.example.fishingmanagementbackend.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.dto.FishermanDTO;
import com.example.fishingmanagementbackend.dto.RegistrationDTO;
import com.example.fishingmanagementbackend.enumerations.FishermanCategory;
import com.example.fishingmanagementbackend.exceptions.BadRequestException;
import com.example.fishingmanagementbackend.exceptions.ForbiddenException;
import com.example.fishingmanagementbackend.model.Authority;
import com.example.fishingmanagementbackend.model.Fisherman;
import com.example.fishingmanagementbackend.model.ProfessionalFisherman;
import com.example.fishingmanagementbackend.model.RecreationalFisherman;
import com.example.fishingmanagementbackend.model.User;
import com.example.fishingmanagementbackend.repository.FishermanRepository;
import com.example.fishingmanagementbackend.repository.ProfessionalFishermanRepository;
import com.example.fishingmanagementbackend.repository.UserRepository;

@Service
public class FishermanService {

    @Autowired
    private FishermanRepository fishermanRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProfessionalFishermanRepository professionalFishermanRepository ;
    
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
    
    /**Metoda koja vraca stranu ribolovaca, umesto svih ribolovaca, za potrebe paginacije*/
    public Map<String, Object> getAllFishermans(Pageable page) {
        List<FishermanDTO> fishermansDTO = new ArrayList<>();
        List<Fisherman> fishermans;
        
        Page<Fisherman> fishermansPage = fishermanRepository.findAll(page);
        
        fishermans = fishermansPage.getContent();
        
        for(Fisherman f: fishermans) {
            fishermansDTO.add(new FishermanDTO(f));
        }
        
        Map<String, Object> fishermansResponse = new HashMap<>();
        fishermansResponse.put("fishermans", fishermansDTO);
        fishermansResponse.put("currentPageNumber", fishermansPage.getNumber());
        fishermansResponse.put("totalElements", fishermansPage.getTotalElements());
        fishermansResponse.put("totalPages", fishermansPage.getTotalPages());
        
        return fishermansResponse;
    }

    /**Pretraga ribolovaca po imenu i prezimenu*/
    public Map<String, Object> searchFishermans(Pageable page, String firstName, String lastName, int cat) {
    
        List<FishermanDTO> fishermansDTO = new ArrayList<>();
        List<Fisherman> fishermans;
       
        Page<Fisherman> fishermansPage;
        if(cat == -1) {
            fishermansPage = fishermanRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(page, firstName, lastName);
        } else {
            FishermanCategory category;
            category = (cat == 0 ? FishermanCategory.RECREATIONAL : FishermanCategory.PROFESSIONAL);
            fishermansPage = fishermanRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndCategory(page, firstName, lastName, category);   
        }
       
        fishermans = fishermansPage.getContent();
       
        for(Fisherman f: fishermans) {
            fishermansDTO.add(new FishermanDTO(f));
        }
        
        Map<String, Object> fishermansResponse = new HashMap<>();
        fishermansResponse.put("fishermans", fishermansDTO);
        fishermansResponse.put("currentPageNumber", fishermansPage.getNumber());
        fishermansResponse.put("totalElements", fishermansPage.getTotalElements());
        fishermansResponse.put("totalPages", fishermansPage.getTotalPages());
         
        return fishermansResponse;
    }
    
    /** Vraca sve ribolovce sa nepotvrdjenim ulovima za prikaz ribocuvaru 
     * koji je nadlezan za ribolovnu vodu na kojoj su ti ulovi ostvareni*/
    public Map<String, Object> getAllFishermansWithNonConfirmedCatches(Principal principal, Pageable page) {
        Long loggedKeeperId = userRepository.findByUsername(principal.getName()).getId();
        
        List<Fisherman> fishermansWithNonConfirmedCatches;
        List<FishermanDTO> fishermansDTO = new ArrayList<>();
        
        Page<Fisherman> fishermansPage = fishermanRepository.findAllFishermansWithNonConfirmedCatches(page, loggedKeeperId);
        fishermansWithNonConfirmedCatches = fishermansPage.getContent();
        
        for(Fisherman f : fishermansWithNonConfirmedCatches) {
            fishermansDTO.add(new FishermanDTO(f));
        }
        
        Map<String, Object> fishermansResponse = new HashMap<>();
        fishermansResponse.put("fishermans", fishermansDTO);
        fishermansResponse.put("currentPageNumber", fishermansPage.getNumber());
        fishermansResponse.put("totalElements", fishermansPage.getTotalElements());
        fishermansResponse.put("totalPages", fishermansPage.getTotalPages());
        
        return fishermansResponse;
    }
    
    public FishermanDTO getFishermanById(Long id, Principal principal) {
        
        // Branim ribolovcima da vide i menjaju podatke drugih ribolovaca
        if(userRepository.findByUsername(principal.getName()).getAuthorities().iterator().next().getName().equals("ROLE_FISHERMAN")) {
            if(!userRepository.findByUsername(principal.getName()).getId().equals(id)) {
                throw new ForbiddenException("Nemate privilegije da vidite podatke ovog ribolovca");
            }    
        }
        
        Fisherman fisherman = fishermanRepository.getReferenceById(id);
        // Ukoliko je u pitanju privredni ribolovac moram ga dobaviti iz specificnog repozitorijuma zbog dodatnog polja registry number
        if(fisherman.getCategory().equals(FishermanCategory.PROFESSIONAL)) {
            ProfessionalFisherman professionalFisherman = professionalFishermanRepository.getReferenceById(id);
            return new FishermanDTO(professionalFisherman);
        }
        
        return new FishermanDTO(fisherman);
    }
    
    /**Registrovanje novog ribolovca*/
    public Fisherman createNewFisherman(RegistrationDTO registrationDTO) throws BadRequestException {
        User existingUser = userService.findUserByUsername(registrationDTO.getUsername());
        //TODO: vratiti smisleniji exception
        if(existingUser != null) {
            return null;
        }
        
        if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new BadRequestException("Lozinke nisu jednake.");
        }
        
        Fisherman fisherman;
        
        if(registrationDTO.getCategory().equals(FishermanCategory.RECREATIONAL)) {
            fisherman = createNewRecreationalFisherman(registrationDTO);
        } else {
            fisherman = createNewProfessionalFisherman(registrationDTO);
        }
        
        // Dodela role odnosno privilegija
        Set<Authority> authorities = authService.findByName("ROLE_FISHERMAN");
        fisherman.setAuthorities(authorities);
        
        fisherman = fishermanRepository.save(fisherman);
        
        // Saljemo mejl sa linkom za potvrdu registracije
        try {
            emailService.sendMailAsync(fisherman, registrationDTO.getFirstName());
        } catch (MailException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return fisherman;
    }
    
    public FishermanDTO updateFisherman(Long id, FishermanDTO fishermanDTO, Principal principal) throws ForbiddenException {
        
        if(!userRepository.findByUsername(principal.getName()).getId().equals(id)) {
            throw new ForbiddenException("Nemate privilegije da menjate podatke ovog ribolovca");
        }
        
        if(fishermanDTO.getCategory().equals(FishermanCategory.PROFESSIONAL)) {
            ProfessionalFisherman professionalFisherman = professionalFishermanRepository.getReferenceById(id);
            professionalFisherman.setFirstName(fishermanDTO.getFirstName());
            professionalFisherman.setLastName(fishermanDTO.getLastName());
            professionalFisherman.setDateOfBirth(fishermanDTO.getDateOfBirth());
            professionalFisherman.setAddress(fishermanDTO.getAddress());
            professionalFisherman.setCity(fishermanDTO.getCity());
            professionalFisherman.setCategory(fishermanDTO.getCategory());
            professionalFisherman.setRegistryNumber(fishermanDTO.getRegistryNumber());
            
            professionalFisherman = fishermanRepository.save(professionalFisherman);
            return new FishermanDTO(professionalFisherman);
        }
        
        Fisherman fisherman = fishermanRepository.getReferenceById(id);
        
        fisherman.setFirstName(fishermanDTO.getFirstName());
        fisherman.setLastName(fishermanDTO.getLastName());
        fisherman.setDateOfBirth(fishermanDTO.getDateOfBirth());
        fisherman.setAddress(fishermanDTO.getAddress());
        fisherman.setCity(fishermanDTO.getCity());
        fisherman.setCategory(fishermanDTO.getCategory());
        
        fisherman = fishermanRepository.save(fisherman);
        return new FishermanDTO(fisherman) ;
    }
    
    private RecreationalFisherman createNewRecreationalFisherman(RegistrationDTO registrationDTO) {
        
        RecreationalFisherman fisherman = new RecreationalFisherman();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        fisherman.setUsername(registrationDTO.getUsername());
        fisherman.setPassword(encoder.encode(registrationDTO.getPassword()));
        // Nalog ribolovca nije enabled dok ga ne verifikuje preko mejla
        fisherman.setEnabled(false);
        fisherman.setFirstName(registrationDTO.getFirstName());
        fisherman.setLastName(registrationDTO.getLastName());
        fisherman.setDateOfBirth(registrationDTO.getDateOfBirth());
        fisherman.setAddress(registrationDTO.getAddress());
        fisherman.setCity(registrationDTO.getCity());
        fisherman.setCategory(registrationDTO.getCategory());
        
        return fisherman;
    }
    
    private ProfessionalFisherman createNewProfessionalFisherman(RegistrationDTO registrationDTO) {
        
        ProfessionalFisherman fisherman = new ProfessionalFisherman(registrationDTO.getRegistryNumber());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        fisherman.setUsername(registrationDTO.getUsername());
        fisherman.setPassword(encoder.encode(registrationDTO.getPassword()));
        // Nalog ribolovca nije enabled dok ga ne verifikuje preko mejla
        fisherman.setEnabled(false);
        fisherman.setFirstName(registrationDTO.getFirstName());
        fisherman.setLastName(registrationDTO.getLastName());
        fisherman.setDateOfBirth(registrationDTO.getDateOfBirth());
        fisherman.setAddress(registrationDTO.getAddress());
        fisherman.setCity(registrationDTO.getCity());
        fisherman.setCategory(registrationDTO.getCategory());
        
        return fisherman;
        
    }
    
    public FishermanCategory getCategoryOfLoggedFisherman(Principal principal) {
        Fisherman fisherman = (Fisherman) userRepository.findByUsername(principal.getName());
        return fisherman.getCategory();
    }
}
