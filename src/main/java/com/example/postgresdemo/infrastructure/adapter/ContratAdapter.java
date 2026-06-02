package com.example.postgresdemo.infrastructure.adapter;

import com.example.postgresdemo.infrastructure.model.Contrat;
import com.example.postgresdemo.infrastructure.repository.ContratRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
public class ContratAdapter {
    private final ContratRepository contratRepository;

    public ContratAdapter(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }


    public Contrat findById(UUID id) {
        return contratRepository.getReferenceById(id);
    }

    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }
}

