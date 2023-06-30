package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fishingmanagementbackend.model.FishSpecies;

@Repository
public interface FishSpeciesRepository extends JpaRepository<FishSpecies, Long>{

}
