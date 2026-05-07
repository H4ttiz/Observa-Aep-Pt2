package com.unicesumar.observa_aep.dto.endereco;

public record EnderecoResponseDTO(
        Long id,
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado
) {}
