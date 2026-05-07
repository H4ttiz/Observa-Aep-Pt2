package com.unicesumar.observa_aep.repositiry;


import com.unicesumar.observa_aep.model.HistoricoSolicitacao;
import com.unicesumar.observa_aep.model.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoSolicitacaoRepository extends JpaRepository<HistoricoSolicitacao, Long> {
    List<HistoricoSolicitacao> findBySolicitacaoOrderByDataMovimentacaoDesc(Solicitacao solicitacao);
}
