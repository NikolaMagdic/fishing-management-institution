package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.model.Penalized;

public interface PenalizedRepository extends JpaRepository<Penalized, Long>{

    @Query(value = "SELECT p.id, p.date, p.report, p.penalty_id, p.fisherman_id, p.keeper_id FROM penalized p LEFT OUTER JOIN penalty pt ON p.penalty_id = pt.id WHERE p.fisherman_id = ?1", nativeQuery = true)
    List<Penalized> findAllByFisherman(Long fishermanId);
}
