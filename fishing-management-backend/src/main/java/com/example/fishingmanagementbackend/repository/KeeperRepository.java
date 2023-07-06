package com.example.fishingmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fishingmanagementbackend.model.Keeper;

@Repository
public interface KeeperRepository extends JpaRepository<Keeper, Long>{

}
