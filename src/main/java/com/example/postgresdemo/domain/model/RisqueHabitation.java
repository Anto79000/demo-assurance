package com.example.postgresdemo.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class RisqueHabitation extends Risque {

    private Integer nombrePiece;
    private String typeHabitation;
}
