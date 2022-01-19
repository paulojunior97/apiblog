package com.paulojunior97.apiblog.domain.exceptions;

public class SenhaUsuarioObrigatorioException extends ValidacaoException {

    public SenhaUsuarioObrigatorioException() {
        super("Senha do usuário é um parâmetro obrigatório.", 400);
    }

}
