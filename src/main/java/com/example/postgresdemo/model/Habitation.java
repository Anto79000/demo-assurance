package com.example.postgresdemo.model;

import jakarta.persistence.*;
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