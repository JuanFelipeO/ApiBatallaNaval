package com.umanizales.apibatallanaval.model;

import com.umanizales.apibatallanaval.controller.UsuarioController;
import com.umanizales.apibatallanaval.model.dto.DistribucionBarcoDTO;
import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Tablero;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import com.umanizales.apibatallanaval.repository.UsuarioRepository;
import com.umanizales.apibatallanaval.service.ListaDEService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    public void organizarBarco(int id, String x, String y, byte orientacion, Usuario jugador1,
                               Usuario jugador2, ListaDEService listaDEService)
    {
        jugador1 =tableroJugador1.getJugador();
        jugador2 =tableroJugador2.getJugador();

        if (jugador1 == tableroJugador1.jugador)
        {
            listaDEService.getListaBarcos();
            DistribucionBarcoDTO barco = new DistribucionBarcoDTO(distribucionBarcoDTO.getBarco());
        }
       else
        {

        }
    }

    public boolean disparar(int x, int y,ListaDE listaDE)
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