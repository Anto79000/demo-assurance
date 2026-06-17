package com.example.postgresdemo.domain.service;

import com.example.postgresdemo.domain.model.*;
import com.example.postgresdemo.exception.RisqueIncoherentException;
import com.example.postgresdemo.infrastructure.adapter.RisqueAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RisqueService {

    public static final String REGEX_IMMATRICULATION = "^[A-Z]{2}-\\d{3}-[A-Z]{2}$";

    private final RisqueAdapter risqueAdapter;

    public Risque findById(UUID id) {
        return risqueAdapter.findById(id);
    }

    public List<Risque> findAll(LocalDate dateRecherche) {
        return risqueAdapter.findAll(dateRecherche);
    }

    public Risque postRisque(RisqueAEnregistrer risqueAEnregistrer) throws RisqueIncoherentException {
        if (risqueAEnregistrer == null) {
            throw new RisqueIncoherentException("Le risque doit etre defini");
        }
        controleCoherenceRisque(risqueAEnregistrer);
        controleImmatriculationVehicule(risqueAEnregistrer.getVehicule());

        TypeRisque typeRisque = determinerTypeRisque(risqueAEnregistrer);
        LocalDate dateEffet = determinerDateEffet(risqueAEnregistrer.getDateEffet());
        if (typeRisque == TypeRisque.HABITATION) {
            return postRisqueHabitation(risqueAEnregistrer.getHabitation(), dateEffet);
        } else if (typeRisque == TypeRisque.VEHICULE) {
            return postRisqueVehicule(risqueAEnregistrer.getVehicule(), dateEffet);
        } else {
            throw new RuntimeException("Type de risque non reconnu");
        }
    }

    private LocalDate determinerDateEffet(LocalDate dateEffet) {
        return dateEffet != null ? dateEffet : LocalDate.now();
    }

    private Risque postRisqueHabitation(HabitationAEnregistrer habitation, LocalDate dateEffet) {
        RisqueHabitation risqueHabitation = RisqueHabitation.builder()
                .nombrePiece(habitation.getNombrePiece())
                .typeHabitation(habitation.getTypeHabitation())
                .idRisque(null)
                .typeRisque(TypeRisque.HABITATION)
                .codeEtat("Actif")
                .dateEffet(dateEffet)
                .dateFin(null)
                .build();
        return risqueAdapter.postRisqueHabitation(risqueHabitation);
    }

    private Risque postRisqueVehicule(VehiculeAEnregistrer vehicule, LocalDate dateEffet) {
        RisqueVehicule risqueVehicule = RisqueVehicule.builder()
                .immatriculation(vehicule.getImmatriculation())
                .marque(vehicule.getMarque())
                .modele(vehicule.getModele())
                .idRisque(null)
                .typeRisque(TypeRisque.VEHICULE)
                .codeEtat("Actif")
                .dateEffet(dateEffet)
                .dateFin(null)
                .build();
        return risqueAdapter.postRisqueVehicule(risqueVehicule);
    }

    private void controleCoherenceRisque(RisqueAEnregistrer risqueAEnregistrer) throws RisqueIncoherentException {
        if (risqueAEnregistrer.getHabitation() == null && risqueAEnregistrer.getVehicule() == null) {
            throw new RisqueIncoherentException("Le risque est soit vehicule, soit habitation");
        }
        if (risqueAEnregistrer.getHabitation() != null && risqueAEnregistrer.getVehicule() != null) {
            throw new RisqueIncoherentException("Le risque ne peut pas etre vehicule et habitation en meme temps");
        }
    }

    private void controleImmatriculationVehicule(VehiculeAEnregistrer vehicule) throws RisqueIncoherentException {
        if (vehicule == null) return;
        if (vehicule.getImmatriculation() == null) {
            throw new RisqueIncoherentException("La plaque d'immatriculation ne doit pas être null");
        }
        if (!vehicule.getImmatriculation().matches(REGEX_IMMATRICULATION)) {
            throw new RisqueIncoherentException("La format de la plaque n'est pas valide");
        }
        if (risqueAdapter.existsByImmatriculation(vehicule.getImmatriculation())) {
            throw new RisqueIncoherentException("La plaque d'immatriculation existe deja");
        }
    }

    private TypeRisque determinerTypeRisque(RisqueAEnregistrer risqueAEnregistrer) {
        if (risqueAEnregistrer.getVehicule() != null) {
            return TypeRisque.VEHICULE;
        } else {
            return TypeRisque.HABITATION;
        }
    }
}
