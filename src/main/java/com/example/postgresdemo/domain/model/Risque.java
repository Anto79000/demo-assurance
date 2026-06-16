package com.example.postgresdemo.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@SuperBuilder
@Getter
public abstract class Risque {
    private UUID idRisque;
    private String typeRisque;
    private String codeEtat;
    private LocalDate dateEffet;
    private LocalDate dateFin;
}

