package com.umanizales.apibatallanaval.service;


import com.umanizales.apibatallanaval.model.dto.CasillaBarco;
import com.umanizales.apibatallanaval.model.dto.Coordenada;
import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Barco;
import com.umanizales.apibatallanaval.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service  //Application 1 mismo tablero para los n usuarios
public class TableroService {
    private CasillaBarco[][] tableroBarcos;
    private int contadorAciertos=0;
    private int contadorErrores=0;
    private int contEscondidos=0;
    private boolean estadoJuego=false;

    private ListaDEService ListaDEService;

    @Autowired
    public TableroService(ListaDEService ListaDEService) {
        this.ListaDEService = ListaDEService;
    }

    public ResponseEntity<Object> inicializarTablero(int filas, int cols)
    {
        if(filas <0 || cols <0)
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.MESSAGE_ROWS_COLS_POSITIVE,null,
                            Constants.ERROR_ROWS_COLS_POSITIVE)
                    , HttpStatus.CONFLICT);
        }
        tableroBarcos = new CasillaBarco[filas][cols];
        return new ResponseEntity<>(
                new RespuestaDTO(Constants.SUCCESSFUL,null,null),HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> esconderBarco(String codigo, Coordenada coordenada)
    {
        if(coordenada.getFila() <0 || coordenada.getCol() <0)
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.MESSAGE_ROWS_COLS_POSITIVE,null,
                            Constants.ERROR_ROWS_COLS_POSITIVE)
                    , HttpStatus.CONFLICT);
        }
        ///buscar el Barco en la lista
        Barco BarcoEsconder= ListaDEService.encontrarBarcoxCodigo(codigo);
        if(BarcoEsconder!=null)
        {
            //Validar coordena y espacio este libre
            if(validarCoordenada(coordenada))
            {
                //Validar que no este ocupada
                if(tableroBarcos[coordenada.getFila()][coordenada.getCol()]==null)
                {
                    tableroBarcos[coordenada.getFila()][coordenada.getCol()]=
                            new CasillaBarco(BarcoEsconder,false);
                    contEscondidos++;
                    if(contEscondidos == ListaDEService.contarNodos())
                    {
                        estadoJuego=true;
                    }
                    return new ResponseEntity<>(
                            new RespuestaDTO(Constants.SUCCESSFUL,null,null),
                            HttpStatus.ACCEPTED
                    );

                }
                else
                {
                    return new ResponseEntity<>(
                            new RespuestaDTO(Constants.MESSAGE_BOX_OCUPATED,null,
                                    Constants.ERROR_BOX_OCUPATED)
                            , HttpStatus.CONFLICT);
                }
            }
            else
            {
                return new ResponseEntity<>(
                        new RespuestaDTO(Constants.MESSAGE_COORD_NOT_VALIDATE,null,
                                Constants.ERROR_COORD_NOT_VALIDATE)
                        , HttpStatus.CONFLICT);
            }
        }
        else
        {
            return new ResponseEntity<>(new RespuestaDTO(Constants.DATA_NOT_FOUND,
                    null
                    ,Constants.ERROR_DATA_NOT_FOUND),HttpStatus.NOT_FOUND);
        }
    }


    private boolean validarCoordenada(Coordenada coord)
    {
        if(coord.getFila() < tableroBarcos.length && coord.getCol() < tableroBarcos[0].length)
        {
            return true;
        }
        return false;
    }

    public ResponseEntity<Object> visualizarTablero()
    {
        if(tableroBarcos == null)
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.MESSAGE_BOARD_VOID,null,
                            Constants.ERROR_BOARD_VOID)
                    , HttpStatus.CONFLICT);
        }
        else
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.SUCCESSFUL,tableroBarcos,null),
                    HttpStatus.OK
            );
        }
    }

    public ResponseEntity<Object> buscarBarco(Coordenada coord)
    {
        if(estadoJuego)
        {
            if(validarCoordenada(coord))
            {
                if(tableroBarcos[coord.getFila()][coord.getCol()]!=null
                && !tableroBarcos[coord.getFila()][coord.getCol()].isMarcada())
                {
                    //eliminar el Barco de la lista
                    //ListaDEService.eliminarBarco();
                    tableroBarcos[coord.getFila()][coord.getCol()].setMarcada(true);
                    contadorAciertos++;
                    return this.validarEstadoJuego(true,
                            tableroBarcos[coord.getFila()][coord.getCol()].getBarco());
                }
                else
                {
                    contadorErrores++;
                    return this.validarEstadoJuego(false,null);
                }

            }
            else
            {
                return new ResponseEntity<>(
                        new RespuestaDTO(Constants.MESSAGE_COORD_NOT_VALIDATE,null,
                                Constants.ERROR_COORD_NOT_VALIDATE)
                        , HttpStatus.CONFLICT);
            }
        }
        else
        {
            return new ResponseEntity<>(
                    new RespuestaDTO(Constants.MESSAGE_STATE_GAME_INACTIVE,null,
                            Constants.ERROR_STATE_GAME_INACTIVE)
                    , HttpStatus.CONFLICT);
        }
    }


    private ResponseEntity<Object> validarEstadoJuego(boolean exito, Barco Barco)
    {
        if(exito)
        {
            //Acabó de acertar
            if(contadorAciertos== ListaDEService.contarNodos())
            {
                estadoJuego=false;
                tableroBarcos=null;
                return new ResponseEntity<>(
                        new RespuestaDTO("Has ganado el juego",
                                null
                                ,null),
                        HttpStatus.OK
                );

            }
            else
            {
                return new ResponseEntity<>(
                        new RespuestaDTO(Constants.SUCCESSFUL,
                                Barco
                                ,null),
                        HttpStatus.OK
                );
            }
        }
        else
        {
            //acabó de fallas
            if(contadorErrores >= this.ListaDEService.contarNodos() * Constants.PERCENTAGE_ERROR_GAME)
            {
                estadoJuego=false;
                tableroBarcos=null;
                return new ResponseEntity<>(
                        new RespuestaDTO("HAS PERDIDO",null,
                                "HA SUPERADO EL NUMERO DE OPCIONES POSIBLES")
                        , HttpStatus.CONFLICT);
            }
            else
            {
                return new ResponseEntity<>(
                        new RespuestaDTO("Has fallado",null,null )
                        , HttpStatus.CONFLICT);
            }
        }
    }
}
