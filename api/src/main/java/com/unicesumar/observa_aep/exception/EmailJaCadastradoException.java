package com.unicesumar.observa_aep.exception;

public class EmailJaCadastradoException extends RuntimeException{

    public EmailJaCadastradoException(String email){
        super("Email já cadastrado: " + email);
    }
}
