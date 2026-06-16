package com.example.postgresdemo.infrastructure.repository;

import com.example.postgresdemo.infrastructure.model.RisqueInfra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface RisqueRepository extends JpaRepository<RisqueInfra, UUID> {

    List<RisqueInfra> findByDateEffet(LocalDate dateEffet);

    List<RisqueInfra> findByDateFin(LocalDate dateFin);
}