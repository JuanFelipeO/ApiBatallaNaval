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
    public Tablero tableroJugador2;
    public int numeroBarcos;
    public byte turno;
    public int aciertosJug1;
    public int aciertosJug2;

    public ListaDE listaDE;
    public DistribucionBarcoDTO distribucionBarcoDTO;

   public Juego(int id, Usuario jugador1, Usuario jugador2, int numeroBarcos)
   {
       this.id = id;
       this.numeroBarcos = numeroBarcos;

       //jugador1 = distribucionBarcoDTO.definirUbicacion();
   }

    public void crearTableros(Usuario jugador1, Usuario jugador2, int numeroBarcos)
    {
        if (numeroBarcos > 0 && numeroBarcos <= 9) {
            tableroJugador1 = new Tablero(id, 10, 10, jugador1, listaDE.clonarLista());
            tableroJugador2 = new Tablero(id, 10, 10, jugador2, listaDE.clonarLista());
        }
        else if (numeroBarcos >= 10 && numeroBarcos <= 20) {
            tableroJugador1 = new Tablero(id, 20, 20, jugador1, listaDE.clonarLista());
            tableroJugador2 = new Tablero(id, 20, 20, jugador2, listaDE.clonarLista());
        }
        else if (numeroBarcos > 20 && numeroBarcos <= 30) {
            tableroJugador1 = new Tablero(id, 30, 30, jugador1, listaDE.clonarLista());
            tableroJugador2 = new Tablero(id, 30, 30, jugador2, listaDE.clonarLista());

        }
    }

    public boolean disparar(int x, int y)
    {
        return false;
    }

    public String validarDisparo(int x, int y)
    {
        return null;
    }

    public Usuario validarGanador() //terminar!!!
    {
        return null;
    }
}