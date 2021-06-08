package com.umanizales.apibatallanaval.model;

import com.umanizales.apibatallanaval.model.dto.CoordenadaDTO;
import com.umanizales.apibatallanaval.model.dto.DistribucionBarcoDTO;
import com.umanizales.apibatallanaval.model.entities.Tablero;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Juego {
    public int id;
    public Tablero tableroJugador1;
    public Tablero tableroJugador2;
    public int numeroBarcos;
    public byte turno;
    public int aciertosJug1;
    public int aciertosJug2;

    public ListaDE listaDE;
    public DistribucionBarcoDTO distribucionBarcoDTO;
    public CoordenadaDTO coordenadaDTO;

    public Juego(int id, Usuario jugador1, Usuario jugador2, ListaDE listaDE)
    {
        this.id = id;
        this.listaDE = listaDE;

        crearTableros(id,jugador1,jugador2,listaDE.getCont());
    }

    public void crearTableros(int id, Usuario jugador1, Usuario jugador2, int numeroBarcos)
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

    public Object visualizarTablero1()
    {
        //Object tablero1 = (Object) tableroJugador1;
        return tableroJugador1;
    }

    public Object visualizarTablero2()
    {
        return tableroJugador2;
    }

    // metodo para saber a que tablero se manda el barco
    public Object organizarBarco(int x, int y, byte orientacion, Usuario jugador, int posBarcoLista)
    {
        if (jugador == tableroJugador1.jugador)
        {
            // metodo retorna distribucionBarco obtener dato por posicion
            listaDE.encontrarxPosicion(posBarcoLista);
            DistribucionBarcoDTO barco = tableroJugador1.getListaBarco().encontrarxPosicion(posBarcoLista);
            tableroJugador1.validarExistenciaCoordenada(tableroJugador1,x,y);
            barco.definirUbicacion(barco.sugerirUbicacion(x,y,orientacion));
        }
        else if (jugador == tableroJugador2.jugador)
        {
            // metodo
            listaDE.encontrarxPosicion(posBarcoLista);
            DistribucionBarcoDTO barco = tableroJugador2.getListaBarco().encontrarxPosicion(posBarcoLista);
            tableroJugador2.validarExistenciaCoordenada(tableroJugador2,x,y);
            barco.definirUbicacion(barco.sugerirUbicacion(x,y,orientacion));
        }
        return null;
    }


    public boolean disparar(int x, int y)
    {

        // CoordenadaDTO coordenada = new CoordenadaDTO(x,y,true);

        CoordenadaDTO coordenada = new CoordenadaDTO(x,y,true);
        //CoordenadaDTO coordenada = new CoordenadaDTO(x,y,true);
        return false;
    }

    public String validarDisparo(int x, int y) // preguntar parametros de este metodo???
    {
        return null;
    }
}