package com.paulojunior97.apiblog.domain.exceptions;

public class EmailUsuarioObrigatorioException extends ValidacaoException {

    public EmailUsuarioObrigatorioException() {
        super("Email do usuário é um parâmetro obrigatório.", 400);
    }

}
