package com.example.postgresdemo.controller;


import com.example.postgresdemo.domain.model.Risque;
import com.example.postgresdemo.infrastructure.adapter.RisqueAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@RestController
public class RisqueController {

    private final RisqueAdapter risqueAdapter;

    public RisqueController(RisqueAdapter risqueAdapter) {
        this.risqueAdapter = risqueAdapter;
    }

    @GetMapping(path = "/risques/{id}")
    ResponseEntity<Risque> getRisque(@PathVariable UUID id) {
        final Risque risqueInfra = risqueAdapter.findById(id);
        if (risqueInfra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(risqueInfra);
    }

    @GetMapping("/risques")
    ResponseEntity<List<Risque>> getRisques(@RequestParam(required = false) LocalDate dateRecherche) {
        final List<Risque> risqueInfras = risqueAdapter.findAll(dateRecherche);
        if (risqueInfras == null || risqueInfras.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(risqueInfras);
    }
}
