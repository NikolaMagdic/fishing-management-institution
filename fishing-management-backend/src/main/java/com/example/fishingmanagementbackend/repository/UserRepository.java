package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fishingmanagementbackend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);
}
