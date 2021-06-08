package com.umanizales.apibatallanaval.model.dto;

import com.umanizales.apibatallanaval.model.entities.Barco;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

   public void definirUbicacion(CoordenadaDTO[] coordenadas)
    {
        this.casillas= coordenadas;
    }

    public boolean validarExistenciaCoordenada(CoordenadaDTO coordenada){
        if(casillas!=null) {
            for (CoordenadaDTO coord : casillas) {
                if(coord.equals(coordenada))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public CoordenadaDTO[] sugerirUbicacion(int x, int y, byte orientacion)
    {
        CoordenadaDTO[] casillasSugeridas = new CoordenadaDTO[barco.getNumeroCasillas()];

        for(int i=0; i < casillasSugeridas.length;i++)
        {
            //TODO Verificar que la coordenada este libre
            casillasSugeridas[i]= new CoordenadaDTO(x,y,false);
            if(orientacion==1)//Horizontal
            {
                x++;
            }
            else //Vertical
            {
                y++;
            }
        }
        return casillasSugeridas;
    }

    public Object validarEstadoCoordenada(CoordenadaDTO coordenada)
    {
        if (casillas != null)
        {
            for (CoordenadaDTO coord : casillas)
            {
                if(coord.equals(coordenada))
                {
                    return estado;
                }
            }
        }
        return new ResponseEntity<RespuestaDTO>(new RespuestaDTO("Error",
                null,"Los parametros de la cooordenada se encentran fuera del tablero"), HttpStatus.CONFLICT);
    }
}