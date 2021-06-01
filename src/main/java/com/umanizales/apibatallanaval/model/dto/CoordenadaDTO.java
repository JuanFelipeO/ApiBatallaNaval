package com.umanizales.apibatallanaval.model.dto;

import com.umanizales.apibatallanaval.model.entities.Barco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class CoordenadaDTO implements Serializable
{
    private int x;
    private int y;
    private boolean estado;


    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof CoordenadaDTO)
        {
            CoordenadaDTO objDTO= (CoordenadaDTO) obj;
            if(this.x == objDTO.getX() && this.y == objDTO.getY())
            {
                return true;
            }
        }
        return false;
    }
}
