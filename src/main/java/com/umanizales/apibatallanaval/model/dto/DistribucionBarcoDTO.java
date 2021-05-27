package com.umanizales.apibatallanaval.model.dto;

import com.umanizales.apibatallanaval.model.entities.Barco;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DistribucionBarcoDTO implements Serializable {
    private Barco barco;
    private byte orientacion;
    private String estado;//Tocado, Hundido, Intacto
    private CoordenadaDTO[] casillas;

    public DistribucionBarcoDTO(Barco barco) {
        this.barco = barco;
        this.estado="INTACTO";
    }

    public void definirUbicacion(int x, int y, byte orientacion)
    {
        this.casillas = new CoordenadaDTO[barco.getNumeroCasillas()];
        for(int i=0; i < casillas.length;i++)
        {
            //TODO Verificar que la coordenada este libre
            casillas[i]= new CoordenadaDTO(x,y,false);
            if(orientacion==1)//Horizontal
            {
                x++;
            }
            else //Vetical
            {
                y++;
            }
        }
    }
}
