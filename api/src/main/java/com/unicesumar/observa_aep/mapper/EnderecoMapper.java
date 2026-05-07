package com.unicesumar.observa_aep.mapper;

import com.unicesumar.observa_aep.dto.endereco.EnderecoRequestDTO;
import com.unicesumar.observa_aep.dto.endereco.EnderecoResponseDTO;
import com.unicesumar.observa_aep.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoResponseDTO toResponseDTO(Endereco entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    Endereco toEntity(EnderecoRequestDTO dto);
}
