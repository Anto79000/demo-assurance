package com.example.postgresdemo.infrastructure.repository;

import com.example.postgresdemo.infrastructure.model.Contrat;
import com.example.postgresdemo.infrastructure.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, UUID> {
    List<Personne> findByNomIgnoreCaseAndPrenomIgnoreCase(String nom, String prenom);

    List<Personne> findByNomIgnoreCase(String nom);

    List<Personne> findByPrenomIgnoreCase(String prenom);
}