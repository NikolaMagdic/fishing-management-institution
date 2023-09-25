package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fishingmanagementbackend.model.Penalized;

public interface PenalizedRepository extends JpaRepository<Penalized, Long>{

}
