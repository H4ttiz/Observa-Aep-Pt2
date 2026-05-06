package com.unicesumar.observa_aep.mapper;

import com.unicesumar.observa_aep.dto.usuario.CidadaoRequestDTO;
import com.unicesumar.observa_aep.dto.usuario.UsuarioRequestDTO;
import com.unicesumar.observa_aep.dto.usuario.UsuarioResponseDTO;
import com.unicesumar.observa_aep.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // ===== Response — entidade → DTO de retorno =====
    UsuarioResponseDTO toResponseDTO(Usuario entity);
    List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> entities);

    // ===== Request ADM — DTO de cadastro → entidade =====
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoPor", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    Usuario toEntity(UsuarioRequestDTO dto);

    // ===== Request Cidadão — DTO de auto cadastro → entidade =====
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoPor", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    @Mapping(target = "tipoUsuario", ignore = true) // definido no Service!
    Usuario toEntity(CidadaoRequestDTO dto);
}
