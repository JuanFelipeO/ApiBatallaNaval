package com.umanizales.apibatallanaval.model.dto;

import com.umanizales.apibatallanaval.model.entities.Barco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class CasillaBarco implements Serializable {
    private Barco barco;
    private boolean marcada;

}
