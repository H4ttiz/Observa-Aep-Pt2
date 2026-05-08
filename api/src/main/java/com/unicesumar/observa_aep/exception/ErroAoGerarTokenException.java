package com.unicesumar.observa_aep.exception;

public class ErroAoGerarTokenException extends RuntimeException {
    public ErroAoGerarTokenException() {
        super("Erro ao gerar token de autenticação");
    }
}