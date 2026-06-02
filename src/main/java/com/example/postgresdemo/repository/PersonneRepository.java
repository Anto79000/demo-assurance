package com.example.postgresdemo.repository;

import com.example.postgresdemo.repository.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, UUID> {
    List<Personne> findByNomIgnoreCaseAndPrenomIgnoreCase(String nom, String prenom);

    List<Personne> findByNomIgnoreCase(String nom);

    List<Personne> findByPrenomIgnoreCase(String prenom);
}