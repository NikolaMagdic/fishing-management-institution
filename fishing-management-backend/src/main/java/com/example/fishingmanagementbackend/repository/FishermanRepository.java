package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fishingmanagementbackend.model.Fisherman;

@Repository
public interface FishermanRepository extends JpaRepository<Fisherman, Long>{

}
