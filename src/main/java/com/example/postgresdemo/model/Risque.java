package com.example.postgresdemo.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "risque")
@Getter
public class Risque {

    @Id
    @GeneratedValue
    private UUID idRisque;

    @ManyToOne
    @JoinColumn(name = "id_vehicule")
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "id_habitation")
    private Habitation habitation;

    private String typeRisque;
    private String codeEtat;

    private LocalDate dateEffet;
    private LocalDate dateFin;

    public UUID getIdRisque() {
        return idRisque;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public Habitation getHabitation() {
        return habitation;
    }

    public String getTypeRisque() {
        return typeRisque;
    }

    public String getCodeEtat() {
        return codeEtat;
    }

    public LocalDate getDateEffet() {
        return dateEffet;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void test(){
        Voiture voit = new Voiture("123-ab-123","RENAULT", "CLIO");
        voit.setImmatriculation("toto");
        voit.getImmatriculation();

        Voiture voit2 = new Voiture("az-3-rt", "Mercedes", "Class A");
        String immatriculation = voit.getImmatriculation();
        voit2.setImmatriculation(immatriculation);
        voit2.setImmatriculation(voit.getImmatriculation());
    }
}