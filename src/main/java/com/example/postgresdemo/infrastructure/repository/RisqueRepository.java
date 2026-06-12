package com.example.postgresdemo.infrastructure.repository;

import com.example.postgresdemo.infrastructure.model.Risque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface RisqueRepository extends JpaRepository<Risque, UUID> {

    List<Risque> findByDateEffet(LocalDate dateEffet);

    List<Risque> findByDateFin(LocalDate dateFin);
}