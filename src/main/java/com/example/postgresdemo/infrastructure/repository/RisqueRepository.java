package com.example.postgresdemo.infrastructure.repository;

import com.example.postgresdemo.infrastructure.model.Risque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RisqueRepository extends JpaRepository<Risque, UUID> {

}