package com.example.postgresdemo.infrastructure.repository;

import com.example.postgresdemo.infrastructure.model.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, UUID> {


}