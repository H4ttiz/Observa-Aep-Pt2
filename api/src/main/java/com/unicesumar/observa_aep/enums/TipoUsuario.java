package com.unicesumar.observa_aep.enums;

public enum TipoUsuario {

    C("Cidadão"),
    S("Servidor Publico"),
    G("Gestro"),
    A("Admin");

    private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }
}
