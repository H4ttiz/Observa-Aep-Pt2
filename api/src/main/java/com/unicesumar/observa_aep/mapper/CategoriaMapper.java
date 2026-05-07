package com.unicesumar.observa_aep.mapper;

import com.unicesumar.observa_aep.dto.categoria.CategoriaRequestDTO;
import com.unicesumar.observa_aep.dto.categoria.CategoriaResponseDTO;
import com.unicesumar.observa_aep.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaResponseDTO toResponseDTO(Categoria entity);
    List<CategoriaResponseDTO> toResponseDTOList(List<Categoria> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    Categoria toEntity(CategoriaRequestDTO dto);
}
