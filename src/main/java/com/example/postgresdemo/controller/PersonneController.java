package com.example.postgresdemo.controller;

import com.example.postgresdemo.repository.adapter.PersonneAdapter;
import com.example.postgresdemo.repository.model.Personne;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
public class PersonneController {

    private final PersonneAdapter personneAdapter;

    public PersonneController(PersonneAdapter personneAdapter) {
        this.personneAdapter = personneAdapter;
    }

    @GetMapping(path = "/personnes/{id}")
    ResponseEntity<Personne> getPersonne(@PathVariable UUID id) {
        final Personne personne = personneAdapter.findById(id);
        if (personne == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personne);
    }

    @GetMapping("/personnes")
    ResponseEntity<List<Personne>> getPersonnes(@RequestParam(required = false) String nom, @RequestParam(required = false) String prenom) {
        final List<Personne> personnes = personneAdapter.findAllByFilter(nom, prenom);
        if (personnes == null || personnes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personnes);
    }
}
