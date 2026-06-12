package com.example.postgresdemo.controller;


import com.example.postgresdemo.infrastructure.adapter.RisqueAdapter;
import com.example.postgresdemo.infrastructure.model.Risque;
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
        final Risque risque = risqueAdapter.findById(id);
        if (risque == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(risque);
    }

    @GetMapping("/risques")
    ResponseEntity<List<Risque>> getRisques(@RequestParam(required = false) LocalDate dateRecherche) {
        final List<Risque> risques = risqueAdapter.findAll(dateRecherche);
        if (risques == null || risques.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(risques);
    }
}
