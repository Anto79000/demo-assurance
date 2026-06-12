package com.example.postgresdemo.infrastructure.adapter;


import com.example.postgresdemo.infrastructure.model.Risque;
import com.example.postgresdemo.infrastructure.repository.RisqueRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class RisqueAdapter {
    private final RisqueRepository risqueRepository;

    public RisqueAdapter(RisqueRepository risqueRepository) {
        this.risqueRepository = risqueRepository;
    }

    public Risque findById(UUID id) {
        return risqueRepository.getReferenceById(id);
    }

    public List<Risque> findAll(LocalDate dateRecherche) {
        List<Risque> risques = risqueRepository.findAll(); // L'ensemble des risques de ma table RISQUE

        if (dateRecherche == null) {
            return risques;
        }
        List<Risque> result = new ArrayList<>(); // Ma liste de risques que je veux renvoyer

        // faire une boucle for qui va m'ajouter dans ma liste result tous les Risques pour lesquels la méthode
        // rechercheDate est vraie
        for (int i = 0; i < risques.size(); i++) {
            Risque risque = risques.get(i);
            System.out.println(risque);
            if (rechercheDate(risque, dateRecherche)) {
                result.add(risque);
            }
        }

        return result;
    }


    private boolean rechercheDate(Risque risque, LocalDate dateRecherche) {
        return (risque.getDateEffet().isBefore(dateRecherche) || risque.getDateEffet().isEqual(dateRecherche))
                &&
                (risque.getDateFin() == null
                        || (risque.getDateFin().isAfter(dateRecherche) || risque.getDateFin().isEqual(dateRecherche)));

    }
}
