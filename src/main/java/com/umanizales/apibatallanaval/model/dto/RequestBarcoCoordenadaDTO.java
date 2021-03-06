package com.umanizales.apibatallanaval.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class RequestBarcoCoordenadaDTO implements Serializable
{
    private String codigo;
    private CoordenadaDTO coordenada;
}
