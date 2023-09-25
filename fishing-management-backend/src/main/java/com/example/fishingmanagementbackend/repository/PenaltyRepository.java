package com.example.fishingmanagementbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fishingmanagementbackend.model.Penalty;

public interface PenaltyRepository extends JpaRepository<Penalty, Long>{

    @Query(value = "SELECT p.id, p.name, p.description, p.fine FROM penalty p LEFT OUTER JOIN penalized pf ON p.id = pf.penalty_id WHERE fisherman_id = ?1", nativeQuery = true)
    List<Penalty> findAllByFisherman(Long fishermanId);
}
