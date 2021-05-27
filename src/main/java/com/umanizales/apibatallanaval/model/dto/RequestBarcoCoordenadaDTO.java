package com.umanizales.apipaseoperros.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class RequestPerroCoordenadaDTO implements Serializable {
    private String codigo;
    private Coordenada coordenada;
}
