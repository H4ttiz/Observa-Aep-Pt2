package com.unicesumar.observa_aep.repositiry;

import com.unicesumar.observa_aep.enums.NivelPrioridade;
import com.unicesumar.observa_aep.enums.StatusSolicitacao;
import com.unicesumar.observa_aep.model.Solicitacao;
import com.unicesumar.observa_aep.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    List<Solicitacao> findBySolicitante(Usuario solicitante);
    List<Solicitacao> findByStatus(StatusSolicitacao status);
    List<Solicitacao> findByAtendente(Usuario atendente);
    List<Solicitacao> findByStatusAndPrioridade(StatusSolicitacao status, NivelPrioridade prioridade);
}
