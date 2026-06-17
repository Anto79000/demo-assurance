package com.example.postgresdemo.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class RisqueAEnregistrer {
    private VehiculeAEnregistrer vehicule;
    private HabitationAEnregistrer habitation;
    private LocalDate dateEffet;
}
