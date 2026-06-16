package com.example.postgresdemo.infrastructure.adapter;


import com.example.postgresdemo.domain.model.Risque;
import com.example.postgresdemo.infrastructure.mapper.RisqueMapper;
import com.example.postgresdemo.infrastructure.model.RisqueInfra;
import com.example.postgresdemo.infrastructure.repository.RisqueRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class RisqueAdapter {
    private final RisqueRepository risqueRepository;
    private final RisqueMapper risqueMapper;

    public RisqueAdapter(RisqueRepository risqueRepository, RisqueMapper risqueMapper) {
        this.risqueRepository = risqueRepository;
        this.risqueMapper = risqueMapper;
    }

    public Risque findById(UUID id) {
        RisqueInfra res = risqueRepository.getReferenceById(id);
        return risqueMapper.toDomain(res);

    }

    public List<Risque> findAll(LocalDate dateRecherche) {
        List<RisqueInfra> risqueInfras = risqueRepository.findAll(); // L'ensemble des risques de ma table RISQUE

        if (dateRecherche == null) {
            return risqueMapper.toDomain(risqueInfras);
        }
        List<RisqueInfra> result = new ArrayList<>(); // Ma liste de risques que je veux renvoyer

        // faire une boucle for qui va m'ajouter dans ma liste result tous les Risques pour lesquels la méthode
        // rechercheDate est vraie
        for (int i = 0; i < risqueInfras.size(); i++) {
            RisqueInfra risqueInfra = risqueInfras.get(i);
            System.out.println(risqueInfra);
            if (rechercheDate(risqueInfra, dateRecherche)) {
                result.add(risqueInfra);
            }
        }

        return risqueMapper.toDomain(result);
    }


    private boolean rechercheDate(RisqueInfra risqueInfra, LocalDate dateRecherche) {
        return (risqueInfra.getDateEffet().isBefore(dateRecherche) || risqueInfra.getDateEffet().isEqual(dateRecherche))
                &&
                (risqueInfra.getDateFin() == null
                        || (risqueInfra.getDateFin().isAfter(dateRecherche) || risqueInfra.getDateFin().isEqual(dateRecherche)));

    }
}
