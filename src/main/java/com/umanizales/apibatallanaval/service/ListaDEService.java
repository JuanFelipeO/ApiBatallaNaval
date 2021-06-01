package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.ListaDE;
import com.umanizales.apibatallanaval.model.NodoDE;
import com.umanizales.apibatallanaval.model.dto.DistribucionBarcoDTO;
import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Barco;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaDEService {
    private ListaDE listaBarcos;

    public ListaDEService() {
        listaBarcos = new ListaDE();
    }

    public ResponseEntity<Object> adicionarDistribucionBarco(DistribucionBarcoDTO distribucion)
    {
        listaBarcos.adicionarNodoDE(distribucion);
        return new ResponseEntity<>(new RespuestaDTO("Exitoso","Barco adicionado"
                ,null), HttpStatus.OK);
    }

    public ResponseEntity<Object> visualizarListaDE()
    {
        return new ResponseEntity<>(new RespuestaDTO("Exitoso", listarDatos()
                ,null), HttpStatus.OK);
    }

    private List<DistribucionBarcoDTO> listarDatos()
    {
        List<DistribucionBarcoDTO> listado = new ArrayList<>();
        //Ciclo para recorrer mi lista de de principio a fin
        NodoDE temp = listaBarcos.getCabeza();
        while(temp!=null)
        {
            listado.add((DistribucionBarcoDTO) temp.getDato());
            temp= temp.getSiguiente();
        }
        return listado;
    }
    public Barco encontrarBarcoxCodigo(String codigo)
    {
        return (Barco) this.listaBarcos.encontrarDatoxCodigo(codigo);
    }

    public int contarNodos()
    {
        return listaBarcos.getCont();
    }

    public int obtenerContadorLista()
    {
        return listaBarcos.getCont();
    }
}