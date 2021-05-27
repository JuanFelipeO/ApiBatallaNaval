package com.umanizales.apibatallanaval.repository;

import com.umanizales.apibatallanaval.model.entities.Barco;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BarcoRepository extends CrudRepository<Barco,Integer> {

    @Query("SELECT barquito FROM Barco barquito WHERE barquito.numeroCasillas=?1")//JQL
    Barco encontrarBarcoPorNumeroCasillas(short numeroCasillas);
}
