package com.unicesumar.observa_aep.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoRequestDTO(
        @NotBlank(message = "CEP é obrigatório")
        @Size(max = 10)
        String cep,

        @NotBlank(message = "Logradouro é obrigatório")
        @Size(max = 150)
        String logradouro,

        @NotBlank(message = "Número é obrigatório")
        @Size(max = 10)
        String numero,

        @Size(max = 100)
        String complemento,

        @NotBlank(message = "Bairro é obrigatório")
        @Size(max = 100)
        String bairro,

        @NotBlank(message = "Cidade é obrigatória")
        @Size(max = 100)
        String cidade,

        @NotBlank(message = "Estado é obrigatório")
        @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
        String estado
) {}
