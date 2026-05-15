package com.unicesumar.observa_aep.repositiry;

import com.unicesumar.observa_aep.enums.TipoUsuario;
import com.unicesumar.observa_aep.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<UserDetails> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);

    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
    List<Usuario> findByAtivo(boolean ativo);
}
