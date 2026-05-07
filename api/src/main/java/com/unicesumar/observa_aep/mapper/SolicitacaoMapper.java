package com.unicesumar.observa_aep.mapper;

import com.unicesumar.observa_aep.dto.solicitacao.SolicitacaoRequestDTO;
import com.unicesumar.observa_aep.dto.solicitacao.SolicitacaoResponseDTO;
import com.unicesumar.observa_aep.model.Solicitacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SolicitacaoMapper {

    @Mapping(target = "nomeCategoria", source = "categoria.categoria")
    @Mapping(target = "nomeAtendente", source = "atendente.nome")
    @Mapping(target = "nomeSolicitante", ignore = true)
    @Mapping(target = "endereco", source = "endereco")
    SolicitacaoResponseDTO toResponseDTO(Solicitacao entity);

    List<SolicitacaoResponseDTO> toResponseDTOList(List<Solicitacao> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "solicitante", ignore = true)
    @Mapping(target = "atendente", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "prioridade", ignore = true)
    @Mapping(target = "dataSolicitada", ignore = true)
    @Mapping(target = "dataPrazo", ignore = true)
    @Mapping(target = "dataFinalizada", ignore = true)
    @Mapping(target = "observacao", ignore = true)
    Solicitacao toEntity(SolicitacaoRequestDTO dto);
}
