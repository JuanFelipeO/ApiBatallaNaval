package com.umanizales.apibatallanaval.service;

import com.umanizales.apibatallanaval.model.dto.RespuestaDTO;
import com.umanizales.apibatallanaval.model.entities.Usuario;
import com.umanizales.apibatallanaval.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<Object> findAll()
    {
        return new ResponseEntity<>(new RespuestaDTO("Exitoso",
                usuarioRepository.findAll(),null), HttpStatus.OK);
    }

    public ResponseEntity<Object> create(Usuario usuario)
    {
        try
        {
            Usuario usuarioGuardado= usuarioRepository.save(usuario);
            return new ResponseEntity<>(new RespuestaDTO("Exitoso",
                   usuarioGuardado,null), HttpStatus.OK);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(new RespuestaDTO("Error",
                    null,"Ocurrió un error almacenando el usuario"),
                    HttpStatus.CONFLICT);
        }
    }


    public ResponseEntity<Object> findUsersByRol(short codeRol)
    {
        return new ResponseEntity<>(new RespuestaDTO("Exitoso",
                usuarioRepository.obtenerUsuariosPorRol(codeRol),null), HttpStatus.OK);
    }

}
