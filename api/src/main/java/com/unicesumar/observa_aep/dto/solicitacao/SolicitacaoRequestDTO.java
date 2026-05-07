package com.unicesumar.observa_aep.dto.solicitacao;

import com.unicesumar.observa_aep.dto.endereco.EnderecoRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SolicitacaoRequestDTO(
        @NotBlank(message = "Título é obrigatório")
        @Size(max = 150)
        String titulo,

        @NotBlank(message = "Descrição é obrigatória")
        String descricao,

        @NotNull(message = "Categoria é obrigatória")
        Long categoriaId,

        @NotNull(message = "Endereço é obrigatório")
        EnderecoRequestDTO endereco,

        boolean anonimo

) {}
