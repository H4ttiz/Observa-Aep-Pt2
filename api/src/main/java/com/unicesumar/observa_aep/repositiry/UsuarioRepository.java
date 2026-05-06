package com.unicesumar.observa_aep.repositiry;


import com.unicesumar.observa_aep.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
