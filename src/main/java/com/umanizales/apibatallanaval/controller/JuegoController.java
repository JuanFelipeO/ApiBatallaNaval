package com.umanizales.apibatallanaval.controller;

import com.umanizales.apibatallanaval.model.dto.CoordenadaDTO;
import com.umanizales.apibatallanaval.model.dto.RequestJuegoDTO;
import com.umanizales.apibatallanaval.repository.UsuarioRepository;
import com.umanizales.apibatallanaval.service.JuegoService;
import com.umanizales.apibatallanaval.service.ListaDEService;
import com.umanizales.apibatallanaval.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/juego")
@Validated
public class JuegoController {

    // Inyecto los servicios
    private UsuarioService usuarioService;
    private ListaDEService listaDEService;
    private JuegoService juegoService;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public JuegoController(UsuarioService usuarioService,
                           ListaDEService listaDEService, JuegoService juegoService) {
        this.usuarioService = usuarioService;
        this.listaDEService = listaDEService;
        this.juegoService = juegoService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping(path = "/crear")
    public @ResponseBody ResponseEntity<Object> crearJuego(@RequestBody RequestJuegoDTO juegoDTO)
    {

        // consulta para saber si usuarios existen
        String usuario1 = juegoDTO.getUsuario1();
        String usuario2 = juegoDTO.getUsuario2();
        RequestJuegoDTO juegoDTO1 = new RequestJuegoDTO();
        RequestJuegoDTO juegoDTO2 = new RequestJuegoDTO();
        if (usuarioRepository.obtenerUsuarioPorCorreo(usuario1) == juegoDTO.getUsuario1())
        {

            juegoDTO1.setUsuario1(juegoDTO.getUsuario1());
        }
        if (usuarioRepository.obtenerUsuarioPorCorreo(usuario2) == juegoDTO.getUsuario2())
        {

            juegoDTO2.setUsuario2(juegoDTO2.getUsuario2());
        }
        return juegoService.crearJuego(juegoDTO1.getUsuario1(),juegoDTO2.getUsuario2());
    }

    @PostMapping(path = "/validar")
    public @ResponseBody ResponseEntity<Object> validarCoordenadas(
            @RequestBody CoordenadaDTO[] coordenadas)
    {
        return listaDEService.validarExistenciaCoordenadas(coordenadas);
    }

    @GetMapping(path = "/visualizar")
    public @ResponseBody ResponseEntity<Object> visualizarLista()
    {
        return listaDEService.visualizarListaDE();
    }

}