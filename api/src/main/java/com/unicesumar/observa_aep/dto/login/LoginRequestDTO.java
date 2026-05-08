package com.unicesumar.observa_aep.dto.login;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequestDTO(
        @NotEmpty(message = "Email é Obrigatorio") String email,
        @NotEmpty(message = "Senha é Obrigatoria")String senha
) {
}
