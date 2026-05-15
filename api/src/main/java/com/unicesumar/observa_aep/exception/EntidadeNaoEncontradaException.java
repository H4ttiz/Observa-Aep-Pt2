package com.unicesumar.observa_aep.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{

    public EntidadeNaoEncontradaException(String entidade) {
        super("ERRO: " + entidade + " não encontrada!");
    }
}
