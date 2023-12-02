package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fishingmanagementbackend.model.Keeper;

public interface KeeperRepository extends JpaRepository<Keeper, Long>{

}
