package com.example.postgresdemo.infrastructure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "habitation")
@Getter
public class Habitation {

    @Id
    @GeneratedValue
    private UUID idHabitation;

    private Integer nombrePiece;
    private String typeHabitation;

}