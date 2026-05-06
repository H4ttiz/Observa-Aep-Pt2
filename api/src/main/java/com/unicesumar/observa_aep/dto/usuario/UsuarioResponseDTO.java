package com.unicesumar.observa_aep.dto.usuario;

import com.unicesumar.observa_aep.enums.TipoUsuario;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        TipoUsuario tipoUsuario,
        LocalDateTime dataCriacao,
        boolean ativo,
        String criadoPorNome
) {}
