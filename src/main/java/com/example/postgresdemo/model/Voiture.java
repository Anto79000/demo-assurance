package com.example.postgresdemo.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Voiture {

    private String immatriculation;
    private String marque;
    private String modele;


    public Voiture(String immatriculation, String marque, String modele) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
    }

    private final List<String> VOITURES_FRANCAISES = List.of("RENAULT","ALPINE");

    public boolean isFrancaise(){
        return VOITURES_FRANCAISES.contains(this.marque);

    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }
}
