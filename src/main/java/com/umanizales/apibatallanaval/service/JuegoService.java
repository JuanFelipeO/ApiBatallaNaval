package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.Juego;
import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Tablero;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JuegoService {
    private ListaDEService listaDEService; //inyecto el servicio de ListaDE


    // TERMINAR ESTE CONSTRUCTOR!!!

    public JuegoService(ListaDEService listaDEService) {
        this.listaDEService = listaDEService;
    }

    //private List<Juego> juego;
    private Juego juego;

    public ResponseEntity<Object> crearJuego(Usuario jugador1, Usuario jugador2)
    {
        // validar y crear juego con los 2 tableros

        if (listaDEService.obtenerContadorLista()>0)
        {
            // crear el tablero 1 y el tablero 2
            // crear el juego
            // retorno el juego creado
            juego = new Juego(1,jugador1,jugador2,listaDEService.getListaBarcos());
            return new ResponseEntity<>(new RespuestaDTO("Juego creado",
                    juego,null), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new RespuestaDTO("Error",
                    null,"Aun no ha distribuido la lista DE"),
                    HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Object> validarExistenciaJuego(Tablero tablerojugador1, Tablero tablerojugador2)
    {
        tablerojugador1 = juego.tableroJugador1;
        tablerojugador2 = juego.tableroJugador2;
        if(tablerojugador1 != null && tablerojugador2 != null)
        {
            return new ResponseEntity<>(new RespuestaDTO("El juego ya esta creado",
                    null,null), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new RespuestaDTO("Error",
                    null,"Aun el juego no se encuentra creado"), HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Object> validarGanador(Usuario jugador1, Usuario jugador2)
    {
        try{
            return new ResponseEntity<>(new RespuestaDTO("Ganador",
                    juego.validarGanador(jugador1,jugador2),null), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(new RespuestaDTO("Error",
                    null,"Aun no hay un ganador"), HttpStatus.CONFLICT);
        }
    }
}