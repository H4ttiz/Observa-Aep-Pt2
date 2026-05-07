package com.unicesumar.observa_aep.dto.solicitacao;

import com.unicesumar.observa_aep.dto.endereco.EnderecoResponseDTO;
import com.unicesumar.observa_aep.enums.NivelPrioridade;
import com.unicesumar.observa_aep.enums.StatusSolicitacao;

import java.time.LocalDateTime;

public record SolicitacaoResponseDTO(
        Long id,
        String titulo,
        String descricao,
        String nomeCategoria,
        StatusSolicitacao status,
        NivelPrioridade prioridade,
        EnderecoResponseDTO endereco,
        String nomeSolicitante,
        String cpfSolicitante,
        String nomeAtendente,
        LocalDateTime dataSolicitada,
        LocalDateTime dataPrazo,
        LocalDateTime dataFinalizada,
        String observacao,
        boolean anonimo
) {}
