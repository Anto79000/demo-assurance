package com.example.postgresdemo.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "personne")
@Getter
public class Personne {

    @Id
    @GeneratedValue
    private UUID idPersonne;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

}