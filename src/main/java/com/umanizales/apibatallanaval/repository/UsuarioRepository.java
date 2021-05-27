package com.umanizales.apibatallanaval.repository;

import com.umanizales.apibatallanaval.model.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioRepository  extends CrudRepository<Usuario,Integer> {
    @Query("SELECT usuario FROM Usuario usuario where usuario.tipoUsuario.codigo=?1")
    List<Usuario> obtenerUsuariosPorRol(short codigoRol);
}
