package com.unicesumar.observa_aep.enums;

public enum StatusSolicitacao {

    AGUARDANDO_APROVACAO("Aguardando Aprovação"),
    APROVADA("Aprovada"),
    AGUARDANDO_ATENDIMENTO("Aguardando Atendimento"),
    EM_ANDAMENTO("Em Andamento"),
    FINALIZADO("Finalizado"),
    REJEITADA("Rejeitada");

    private final String descricao;

    StatusSolicitacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
