package com.unicesumar.observa_aep.dto.historico;

import com.unicesumar.observa_aep.enums.StatusSolicitacao;

import java.time.LocalDateTime;

public record HistoricoResponseDTO(
        Long id,
        String nomeResponsavel,
        String comentario,
        StatusSolicitacao statusAnterior,
        StatusSolicitacao statusAtual,
        LocalDateTime dataMovimentacao
) {}