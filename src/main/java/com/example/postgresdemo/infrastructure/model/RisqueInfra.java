package com.example.postgresdemo.infrastructure.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "risque")
@Getter
public class RisqueInfra {

    @Id
    @GeneratedValue
    private UUID idRisque;

    @ManyToOne
    @JoinColumn(name = "id_vehicule")
    private VehiculeInfra vehiculeInfra;

    @ManyToOne
    @JoinColumn(name = "id_habitation")
    private HabitationInfra habitationInfra;

    private String typeRisque;
    private String codeEtat;

    private LocalDate dateEffet;
    private LocalDate dateFin;


}