package com.example.postgresdemo.controller;


import com.example.postgresdemo.controller.model.ResourceWrapper;
import com.example.postgresdemo.domain.model.Risque;
import com.example.postgresdemo.domain.model.RisqueAEnregistrer;
import com.example.postgresdemo.domain.service.RisqueService;
import com.example.postgresdemo.exception.RisqueIncoherentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/risques")
@RequiredArgsConstructor
public class RisqueController {

    private final RisqueService risqueService;

    @GetMapping(path = "/{id}")
    ResponseEntity<Risque> getRisque(@PathVariable UUID id) {
        final Risque risque = risqueService.findById(id);
        if (risque == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(risque);
    }

    @GetMapping
    ResponseEntity<List<Risque>> getRisques(@RequestParam(required = false) LocalDate dateRecherche) {
        final List<Risque> risques = risqueService.findAll(dateRecherche);
        if (risques == null || risques.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(risques);
    }

    @PostMapping
    @ResponseBody
    ResponseEntity<?> postRisque(@RequestBody RisqueAEnregistrer risqueAEnregistrer) {
        try {
            final Risque risque = risqueService.postRisque(risqueAEnregistrer);
            return ResponseEntity.ok(risque); // <-- retourne directement l'objet
        } catch (RisqueIncoherentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResourceWrapper.error(e.getMessage())); // <-- wrapper uniquement pour l'erreur
        }
    }
}
