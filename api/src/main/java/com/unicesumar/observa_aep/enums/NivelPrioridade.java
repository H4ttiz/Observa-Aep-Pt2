package com.unicesumar.observa_aep.enums;

public enum NivelPrioridade {

    BAIXA("Baixa"),
    MODERADA("Moderada"),
    MEDIA("Média"),
    ALTA("Alta"),
    URGENTE("Urgente");

    private final String descricao;

    NivelPrioridade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
