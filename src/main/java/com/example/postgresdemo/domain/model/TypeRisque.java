package com.example.postgresdemo.domain.model;

public enum TypeRisque {
    HABITATION, VEHICULE;

    public static TypeRisque fromString(String value) {
        if (value == null) {
            throw new RuntimeException("La valeur ne peut pas être null");
        }

        try {
            return TypeRisque.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("TypeRisque invalide : " + value);
        }
    }

}
