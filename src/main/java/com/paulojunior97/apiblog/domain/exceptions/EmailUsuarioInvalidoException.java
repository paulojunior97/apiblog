package com.paulojunior97.apiblog.domain.exceptions;

public class EmailUsuarioInvalidoException extends ValidacaoException {

    public EmailUsuarioInvalidoException(String email) {
        super(String.format("Email '%s' informado é inválido.", email), 400);
    }

}
