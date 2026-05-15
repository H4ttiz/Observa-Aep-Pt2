package com.unicesumar.observa_aep.exception;

public class AcessoNegadoException extends RuntimeException{
    public AcessoNegadoException() {
        super("Acesso Negado: Você não possui as permissões necessárias para realizar esta operação.");
    }
}
