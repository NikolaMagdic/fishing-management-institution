package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fishingmanagementbackend.model.FishPopulationModification;

public interface FishStockingRepository extends JpaRepository<FishPopulationModification, Long>{

}
