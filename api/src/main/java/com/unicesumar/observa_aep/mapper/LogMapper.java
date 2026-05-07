package com.unicesumar.observa_aep.mapper;

import com.unicesumar.observa_aep.dto.log.LogResponseDTO;
import com.unicesumar.observa_aep.model.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogMapper {

    @Mapping(target = "nomeUsuario", source = "usuario.nome")
    LogResponseDTO toResponseDTO(Log entity);

    List<LogResponseDTO> toResponseDTOList(List<Log> entities);
}
