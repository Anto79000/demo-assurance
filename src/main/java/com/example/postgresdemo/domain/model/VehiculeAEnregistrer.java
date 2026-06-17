package com.example.postgresdemo.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VehiculeAEnregistrer {
    private String immatriculation;
    private String marque;
    private String modele;
}
