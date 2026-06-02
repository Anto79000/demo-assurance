package com.example.postgresdemo.infrastructure.adapter;

import com.example.postgresdemo.infrastructure.model.Personne;
import com.example.postgresdemo.infrastructure.repository.PersonneRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
public class PersonneAdapter {
    private final PersonneRepository personneRepository;

    public PersonneAdapter(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public List<Personne> findAllByFilter(String nom, String prenom) {
        if (nom != null && prenom != null) {
            return personneRepository.findByNomIgnoreCaseAndPrenomIgnoreCase(nom, prenom);
        } else if (nom != null && prenom == null) {
            return personneRepository.findByNomIgnoreCase(nom);
        } else if (nom == null && prenom != null) {
            return personneRepository.findByPrenomIgnoreCase(prenom);
        } else {
            return personneRepository.findAll();
        }
    }

    public Personne findById(UUID id) {
        return personneRepository.getReferenceById(id);
    }
}
