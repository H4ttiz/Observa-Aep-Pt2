package com.unicesumar.observa_aep.enums;

public enum TipoLog {

    INSERT("Inserção"),
    UPDATE("Atualização"),
    DELETE("Exclusão");

    private final String descricao;

    TipoLog(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
