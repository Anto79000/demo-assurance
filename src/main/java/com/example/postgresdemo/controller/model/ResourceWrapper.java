package com.example.postgresdemo.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceWrapper<T> {

    private T data;
    private String erreur;

    public static <T> ResourceWrapper<T> error(String message) {
        return ResourceWrapper.<T>builder().erreur(message).build();
    }
}
