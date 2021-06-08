package com.umanizales.apibatallanaval.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor

public class RequestOrganizarBarcoDTO implements Serializable
{
    int x;
    int y;
    int posBarcoLista;
    byte orientacion;
    String correo;
}
