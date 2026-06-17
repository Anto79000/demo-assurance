package com.example.postgresdemo.infrastructure.adapter;


import com.example.postgresdemo.domain.model.Risque;
import com.example.postgresdemo.domain.model.RisqueHabitation;
import com.example.postgresdemo.domain.model.RisqueVehicule;
import com.example.postgresdemo.infrastructure.mapper.RisqueMapper;
import com.example.postgresdemo.infrastructure.model.HabitationInfra;
import com.example.postgresdemo.infrastructure.model.RisqueInfra;
import com.example.postgresdemo.infrastructure.model.VehiculeInfra;
import com.example.postgresdemo.infrastructure.repository.HabitationRepository;
import com.example.postgresdemo.infrastructure.repository.RisqueRepository;
import com.example.postgresdemo.infrastructure.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RisqueAdapter {
    private final RisqueRepository risqueRepository;
    private final VehiculeRepository vehiculeRepository;
    private final HabitationRepository habitationRepository;
    private final RisqueMapper risqueMapper;

    public Risque findById(UUID id) {
        RisqueInfra res = risqueRepository.getReferenceById(id);
        return risqueMapper.toDomain(res);

    }

    public boolean existsByImmatriculation(String immatriculation) {
        return vehiculeRepository.existsByImmatriculation(immatriculation);
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

    public Risque postRisqueHabitation(RisqueHabitation risqueHabitation) {
        HabitationInfra habitationInfra = risqueMapper.toHabitationInfra(risqueHabitation);
        habitationInfra = habitationRepository.save(habitationInfra);
        RisqueInfra risqueInfra = risqueMapper.toRisqueInfra(risqueHabitation, habitationInfra);
        RisqueInfra risqueCree = risqueRepository.save(risqueInfra);
        return risqueMapper.toDomain(risqueCree);
    }

    public Risque postRisqueVehicule(RisqueVehicule risqueVehicule) {
        VehiculeInfra vehiculeInfra = risqueMapper.toVehiculeInfra(risqueVehicule);
        vehiculeInfra = vehiculeRepository.save(vehiculeInfra);
        RisqueInfra risqueInfra = risqueMapper.toRisqueInfra(risqueVehicule, vehiculeInfra);
        RisqueInfra risqueCree = risqueRepository.save(risqueInfra);
        return risqueMapper.toDomain(risqueCree);
    }

    private boolean rechercheDate(RisqueInfra risqueInfra, LocalDate dateRecherche) {
        return (risqueInfra.getDateEffet().isBefore(dateRecherche) || risqueInfra.getDateEffet().isEqual(dateRecherche))
                &&
                (risqueInfra.getDateFin() == null
                        || (risqueInfra.getDateFin().isAfter(dateRecherche) || risqueInfra.getDateFin().isEqual(dateRecherche)));

    }

}
