package com.umanizales.apibatallanaval.controller;

import com.umanizales.apibatallanaval.model.entities.Barco;
import com.umanizales.apibatallanaval.service.BarcoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "barco")
@Validated
public class BarcoController {
    private BarcoService barcoService;
    @Autowired
    public BarcoController(BarcoService barcoService) {
        this.barcoService = barcoService;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<Object> findAll()
    {
        return barcoService.findAll();
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Object> create(@RequestBody Barco barco)
    {
        return barcoService.create(barco);
    }
}
