package com.unicesumar.observa_aep.repositiry;

import com.unicesumar.observa_aep.enums.TipoLog;
import com.unicesumar.observa_aep.model.Log;
import com.unicesumar.observa_aep.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByUsuario(Usuario usuario);
    List<Log> findByAcao(TipoLog acao);
}
