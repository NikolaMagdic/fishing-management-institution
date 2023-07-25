package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fishingmanagementbackend.model.Catch;
import com.example.fishingmanagementbackend.model.Fisherman;

import java.util.List;


@Repository 
public interface CatchRepository extends JpaRepository<Catch, Long>{

    List<Catch> findByFisherman(Fisherman fisherman);
    
}
