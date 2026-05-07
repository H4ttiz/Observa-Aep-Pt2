package com.unicesumar.observa_aep.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(
        @NotBlank(message = "Nome da categoria é obrigatório")
        @Size(max = 150)
        String categoria,

        @NotBlank(message = "Descrição é obrigatória")
        String descricao
) {}
