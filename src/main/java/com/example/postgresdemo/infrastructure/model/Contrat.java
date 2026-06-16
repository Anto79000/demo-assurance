package com.example.postgresdemo.infrastructure.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "contrat")
@Getter
public class Contrat {

    @Id
    @GeneratedValue
    private UUID idContrat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_personne", nullable = false)
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "id_risque")
    private RisqueInfra risqueInfra;

    @Column(nullable = false)
    private String typeContrat;

    @Column(nullable = false)
    private BigDecimal tarifAnnuel;

}