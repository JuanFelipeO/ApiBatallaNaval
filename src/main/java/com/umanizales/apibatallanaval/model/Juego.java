package com.umanizales.apibatallanaval.model;

import com.umanizales.apibatallanaval.model.dto.DistribucionBarcoDTO;
import com.umanizales.apibatallanaval.model.entities.Tablero;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Juego
{
    public int id;
    public Tablero tableroJugador1;
    public Tablero TableroJugador2;
    public int numeroBarcos; // preguntar cual es este tipo de dato ???
    public byte turno;
    public int aciertosJug1;
    public int aciertosJug2;

    public ListaDE listaDE;
    public DistribucionBarcoDTO distribucionBarcoDTO;

   public Juego(int id, Usuario jugador1, Usuario jugador2, int numeroBarcos)
   {
       this.id = id;
       this.numeroBarcos = numeroBarcos;
   }

    public boolean disparar(int x, int y)
    {
        return false;
    }

    public String validarDisparo(int x, int y) // preguntar parametros de este metodo???
    {
        return null;
    }

    public Usuario validarGanador() //terminar!!!
    {
        return null;
    }
}