package com.example.postgresdemo.controller;

import com.example.postgresdemo.infrastructure.adapter.ContratAdapter;
import com.example.postgresdemo.infrastructure.model.Contrat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
public class ContratController {

    private final ContratAdapter contratAdapter;

    public ContratController(ContratAdapter contratAdapter) {
        this.contratAdapter = contratAdapter;
    }

    @GetMapping(path = "/contrat/{id}")
    ResponseEntity<Contrat> getContrat(@PathVariable UUID id) {
        final Contrat contrat = contratAdapter.findById(id);
        if (contrat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contrat);
    }

    @GetMapping("/contrats")
    ResponseEntity<List<Contrat>> getContrats() {
        final List<Contrat> contrats = contratAdapter.findAll();
        if (contrats == null || contrats.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contrats);
    }
}
