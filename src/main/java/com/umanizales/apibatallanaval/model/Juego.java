package com.umanizales.apibatallanaval.model;

import com.umanizales.apibatallanaval.controller.TableroController;
import com.umanizales.apibatallanaval.model.dto.DistribucionBarcoDTO;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@Setter
public class Juego {
    public int id;
    public TableroController tableroJugador1;
    public TableroController TableroJugador2;
    public int numeroBarcos; // preguntar cual es este tipo de dato ???
    public byte turno;
    public int aciertosJug1;
    public int aciertosJug2;

    public ListaDE listaDE;
    public DistribucionBarcoDTO distribucionBarcoDTO;

    public Juego(int id, TableroController tableroJugador1, TableroController tableroJugador2,
                 int numeroBarcos, byte turno, int aciertosJug1, int aciertosJug2) {
        this.id = id;
        this.tableroJugador1 = tableroJugador1;
        TableroJugador2 = tableroJugador2;
        this.numeroBarcos = numeroBarcos;
        this.turno = turno;
        this.aciertosJug1 = aciertosJug1;
        this.aciertosJug2 = aciertosJug2;
    }

    public void crearTableros() // terminar!!!
    {
        if (numeroBarcos > 0 && numeroBarcos <= 9)
        {
            // crear tabero aqui
        }
        if (numeroBarcos > 9 && numeroBarcos <= 20)
        {
            // crear tablero aqui
        }
        if (numeroBarcos > 20)
        {
            // crear tablero aqui
        }
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