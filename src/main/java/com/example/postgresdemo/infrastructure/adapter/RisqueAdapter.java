package com.example.postgresdemo.infrastructure.adapter;


import com.example.postgresdemo.infrastructure.model.Risque;
import com.example.postgresdemo.infrastructure.repository.RisqueRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
public class RisqueAdapter {
    private final RisqueRepository risqueRepository;

    public RisqueAdapter(RisqueRepository risqueRepository) {
        this.risqueRepository = risqueRepository;
    }

    public Risque findById(UUID id) {
        return risqueRepository.getReferenceById(id);
    }

    public List<Risque> findAll() {
        return risqueRepository.findAll();
    }
}
