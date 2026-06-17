package com.example.postgresdemo.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@SuperBuilder
@Getter
@NoArgsConstructor
public abstract class Risque {
    private UUID idRisque;
    private TypeRisque typeRisque;
    private String codeEtat;
    private LocalDate dateEffet;
    private LocalDate dateFin;

    public Risque(Risque risque) {
        if (risque == null) return;
        this.idRisque = risque.getIdRisque();
        this.typeRisque = risque.getTypeRisque();
        this.codeEtat = risque.getCodeEtat();
        this.dateEffet = risque.getDateEffet();
        this.dateFin = risque.getDateFin();
    }
}

