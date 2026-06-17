package com.example.postgresdemo.infrastructure.mapper;

import com.example.postgresdemo.domain.model.Risque;
import com.example.postgresdemo.domain.model.RisqueHabitation;
import com.example.postgresdemo.domain.model.RisqueVehicule;
import com.example.postgresdemo.domain.model.TypeRisque;
import com.example.postgresdemo.infrastructure.model.HabitationInfra;
import com.example.postgresdemo.infrastructure.model.RisqueInfra;
import com.example.postgresdemo.infrastructure.model.VehiculeInfra;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RisqueMapper {
    public Risque toDomain(RisqueInfra risqueInfra) {
        if (risqueInfra == null) {
            return null;
        }
        if (risqueInfra.getVehiculeInfra() != null) {
            return RisqueVehicule.builder()
                    .immatriculation(risqueInfra.getVehiculeInfra().getImmatriculation())
                    .marque(risqueInfra.getVehiculeInfra().getMarque())
                    .modele(risqueInfra.getVehiculeInfra().getModele())
                    .idRisque(risqueInfra.getIdRisque())
                    .typeRisque(TypeRisque.fromString(risqueInfra.getTypeRisque()))
                    .codeEtat(risqueInfra.getCodeEtat())
                    .dateEffet(risqueInfra.getDateEffet())
                    .dateFin(risqueInfra.getDateFin())
                    .build();
        } else if (risqueInfra.getHabitationInfra() != null) {
            return RisqueHabitation.builder()
                    .nombrePiece(risqueInfra.getHabitationInfra().getNombrePiece())
                    .typeHabitation(risqueInfra.getHabitationInfra().getTypeHabitation())
                    .idRisque(risqueInfra.getIdRisque())
                    .typeRisque(TypeRisque.fromString(risqueInfra.getTypeRisque()))
                    .codeEtat(risqueInfra.getCodeEtat())
                    .dateEffet(risqueInfra.getDateEffet())
                    .dateFin(risqueInfra.getDateFin())
                    .build();
        } else {
            throw new UnsupportedOperationException("un risque est obligatoirement de type vehicule ou habitation");
        }
    }

    public List<Risque> toDomain(List<RisqueInfra> risqueInfras) {
        return risqueInfras.stream().map(this::toDomain).toList();
    }

    public HabitationInfra toHabitationInfra(RisqueHabitation risqueHabitation) {
        HabitationInfra res = new HabitationInfra();
        res.setTypeHabitation(risqueHabitation.getTypeHabitation());
        res.setNombrePiece(risqueHabitation.getNombrePiece());
        return res;
    }

    public RisqueInfra toRisqueInfra(RisqueHabitation risqueHabitation, HabitationInfra habitationInfra) {
        RisqueInfra res = new RisqueInfra();
        res.setHabitationInfra(habitationInfra);
        res.setTypeRisque(risqueHabitation.getTypeRisque().toString());
        res.setCodeEtat(risqueHabitation.getCodeEtat());
        res.setDateEffet(risqueHabitation.getDateEffet());
        res.setDateFin(risqueHabitation.getDateFin());
        return res;

    }

    public VehiculeInfra toVehiculeInfra(RisqueVehicule risqueVehicule) {
        VehiculeInfra res = new VehiculeInfra();
        res.setMarque(risqueVehicule.getMarque());
        res.setImmatriculation(risqueVehicule.getImmatriculation());
        res.setModele(risqueVehicule.getModele());
        return res;
    }

    public RisqueInfra toRisqueInfra(RisqueVehicule risqueVehicule, VehiculeInfra vehiculeInfra) {
        RisqueInfra res = new RisqueInfra();
        res.setVehiculeInfra(vehiculeInfra);
        res.setTypeRisque(risqueVehicule.getTypeRisque().toString());
        res.setCodeEtat(risqueVehicule.getCodeEtat());
        res.setDateEffet(risqueVehicule.getDateEffet());
        res.setDateFin(risqueVehicule.getDateFin());
        return res;

    }
}
