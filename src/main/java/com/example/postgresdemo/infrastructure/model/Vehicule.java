package com.example.postgresdemo.infrastructure.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "vehicule")
@Getter
public class Vehicule {

    @Id
    @GeneratedValue
    private UUID idVehicule;

    @Column(nullable = false, unique = true)
    private String immatriculation;

    private String marque;
    private String modele;

}
