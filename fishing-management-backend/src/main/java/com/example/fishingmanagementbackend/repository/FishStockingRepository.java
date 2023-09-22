package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fishingmanagementbackend.model.FishStocking;

public interface FishStockingRepository extends JpaRepository<FishStocking, Long>{

}
