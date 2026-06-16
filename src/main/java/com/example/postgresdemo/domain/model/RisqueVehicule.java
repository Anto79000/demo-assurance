package com.example.postgresdemo.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class RisqueVehicule extends Risque {

    private String immatriculation;
    private String marque;
    private String modele;
}








