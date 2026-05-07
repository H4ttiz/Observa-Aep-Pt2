package com.unicesumar.observa_aep.dto.log;

import com.unicesumar.observa_aep.enums.TipoLog;

import java.time.LocalDateTime;

public record LogResponseDTO(
        Long id,
        String nomeUsuario,
        String nomeTabela,
        TipoLog acao,
        String dadosAlterados,
        LocalDateTime dataExecucao
) {}
