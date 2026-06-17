package com.example.postgresdemo.infrastructure.repository;

import com.example.postgresdemo.infrastructure.model.VehiculeInfra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehiculeRepository extends JpaRepository<VehiculeInfra, UUID> {
    boolean existsByImmatriculation(String immatriculation);

}