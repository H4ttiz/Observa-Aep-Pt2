package com.unicesumar.observa_aep.mapper;

import com.unicesumar.observa_aep.dto.historico.HistoricoResponseDTO;
import com.unicesumar.observa_aep.model.HistoricoSolicitacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoricoMapper {

    @Mapping(target = "nomeResponsavel", source = "responsavel.nome")
    HistoricoResponseDTO toResponseDTO(HistoricoSolicitacao entity);

    List<HistoricoResponseDTO> toResponseDTOList(List<HistoricoSolicitacao> entities);
}
