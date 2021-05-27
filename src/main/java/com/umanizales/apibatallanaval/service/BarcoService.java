package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Barco;
import com.umanizales.apibatallanaval.repository.BarcoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BarcoService {
    private BarcoRepository barcoRepository;
    @Autowired
    public BarcoService(BarcoRepository barcoRepository) {
        this.barcoRepository = barcoRepository;
    }

    public ResponseEntity<Object> findAll()
    {
        return new ResponseEntity<>(new RespuestaDTO("Exitoso",
                barcoRepository.findAll(),null), HttpStatus.OK);
    }

    public ResponseEntity<Object> create(Barco barco)
    {
        try
        {
            //Consultar si ya existe un barco con ese número de casilla
            Barco barcoConsulta = barcoRepository.encontrarBarcoPorNumeroCasillas(barco.getNumeroCasillas());
            if(barcoConsulta==null)
            {
                Barco barcoGuardado= barcoRepository.save(barco);
                return new ResponseEntity<>(new RespuestaDTO("Exitoso",
                        barcoGuardado,null), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new RespuestaDTO("Error",
                        null,"Ya existe un barco con ese número de casillas"),
                        HttpStatus.CONFLICT);
            }
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(new RespuestaDTO("Error",
                    null,"Ocurrió un error almacenando el Barco"),
                    HttpStatus.CONFLICT);
        }
    }

}
