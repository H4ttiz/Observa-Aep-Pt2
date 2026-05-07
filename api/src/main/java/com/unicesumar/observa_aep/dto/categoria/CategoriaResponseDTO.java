package com.unicesumar.observa_aep.dto.categoria;

import java.time.LocalDateTime;

public record CategoriaResponseDTO(
        Long id,
        String categoria,
        String descricao,
        LocalDateTime dataCriacao,
        boolean ativo
) {}
