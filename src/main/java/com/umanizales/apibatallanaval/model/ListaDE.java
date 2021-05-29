package com.umanizales.apibatallanaval.model;

import com.umanizales.apibatallanaval.model.dto.CoordenadaDTO;
import com.umanizales.apibatallanaval.model.dto.DistribucionBarcoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ListaDE implements Serializable {
    private NodoDE cabeza;
    private int cont;

    public void adicionarNodoDE(Object dato){
        if(cabeza == null)
        {
            cabeza = new NodoDE(dato);
        }
        else
        {
            //LLamar a mi ayudante y ubicarme en el último
            NodoDE temp = cabeza;
            while(temp.getSiguiente()!=null)
            {
                temp= temp.getSiguiente();
            }
            ///Parado en el ultimo
            temp.setSiguiente(new NodoDE(dato));
            temp.getSiguiente().setAnterior(temp);
        }
        cont++;
    }

    public void adicionarNodoDEAlInicio(Object dato)
    {
        if(cabeza ==null)
        {
            cabeza = new NodoDE(dato);
            cont++;
        }
        else
        {
            NodoDE temp = new NodoDE(dato);
            temp.setSiguiente(cabeza);
            cabeza=temp;
            cont++;
        }
    }

    public String listadoNodoDE()
    {
        String listado="";
        NodoDE temp=cabeza;
        while(temp!=null)
        {
            listado = listado + temp.getDato();
            temp = temp.getSiguiente();
        }

        return listado;
    }

    public int getCont() {
        return cont;
    }

    public Object encontrarDatoxCodigo(String codigo)
    {
        if(cabeza !=null)
        {
            NodoDE temp=cabeza;
            while(temp !=null)
            {
                if(temp.getDato().equals(codigo))
                {
                    return temp.getDato();
                }
                temp = temp.getSiguiente();
            }
        }
        return null;
    }

    public ListaDE clonarLista()
    {
        ListaDE listaCopia= new ListaDE();
        NodoDE temp = cabeza;
        while(temp!=null)
        {
            listaCopia.adicionarNodoDE(temp.getDato());
            temp= temp.getSiguiente();
        }
        return listaCopia;
    }

    public boolean validarExistenciaCoordenadas(CoordenadaDTO[] coordenadas)
    {
        if(cabeza !=null)
        {
            NodoDE temp = cabeza;
            while(temp != null)
            {
                for(CoordenadaDTO coord: coordenadas)
                {
                    if(((DistribucionBarcoDTO) temp.getDato()).validarExistenciaCoordenada(coord))
                    {
                        return true;
                    }
                }
                temp= temp.getSiguiente();
            }
        }
        return false;
    }


}
