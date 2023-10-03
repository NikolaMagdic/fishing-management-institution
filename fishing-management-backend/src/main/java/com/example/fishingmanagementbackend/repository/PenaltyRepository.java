package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fishingmanagementbackend.model.Penalty;

public interface PenaltyRepository extends JpaRepository<Penalty, Long>{

}
