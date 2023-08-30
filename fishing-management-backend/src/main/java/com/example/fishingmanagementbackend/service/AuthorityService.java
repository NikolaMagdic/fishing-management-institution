package com.example.fishingmanagementbackend.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fishingmanagementbackend.model.Authority;
import com.example.fishingmanagementbackend.repository.AuthorityRepository;

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authRepository;
    
    public Set<Authority> findById(Long id) {
        Authority auth = this.authRepository.getReferenceById(id);
        Set<Authority> auths = new HashSet<>();
        auths.add(auth);
        return auths;
    }
    
    public Set<Authority> findByName(String name) {
        Authority auth = this.authRepository.findByName(name);
        Set<Authority> auths = new HashSet<>();
        auths.add(auth);
        return auths;
    }
}
