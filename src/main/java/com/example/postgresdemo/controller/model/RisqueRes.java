package com.example.postgresdemo.controller.model;

import com.example.postgresdemo.domain.model.Risque;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RisqueRes extends Risque {
    private String error;

    public RisqueRes(Risque risque) {
        super(risque);
    }

    public RisqueRes(String error) {
        super();
        this.error = error;
    }
}
