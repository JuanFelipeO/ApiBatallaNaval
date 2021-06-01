package com.umanizales.apibatallanaval.controller;


import com.umanizales.apibatallanaval.model.dto.CoordenadaDTO;
import com.umanizales.apibatallanaval.model.dto.RequestBarcoCoordenadaDTO;
import com.umanizales.apibatallanaval.service.TableroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "escondetubarco")
@Validated
public class BatallaNavalController {
    private TableroService tableroService;
    @Autowired
    public BatallaNavalController(TableroService tableroService) {
        this.tableroService = tableroService;
    }

    @PostMapping(path = "iniciar_tablero")
    public @ResponseBody  ResponseEntity<Object> iniciarTablero(@RequestBody CoordenadaDTO coordenada)
    {
        return tableroService.inicializarTablero(coordenada.getX(), coordenada.getY());
    }

    @GetMapping(path="ver_tablero")
    public @ResponseBody ResponseEntity<Object> visualizarTablero()
    {
        return tableroService.visualizarTablero();
    }

    @PostMapping(path="esconderbarco")
    public @ResponseBody ResponseEntity<Object> esconderBarco(@RequestBody RequestBarcoCoordenadaDTO request)
    {
        return tableroService.esconderBarco(request.getCodigo(),request.getCoordenada());
    }


    @PostMapping(path="buscarbarco")
    public @ResponseBody ResponseEntity<Object> buscarBarco(@RequestBody CoordenadaDTO coordenada  )
    {
        return tableroService.buscarBarco(coordenada);
    }

}