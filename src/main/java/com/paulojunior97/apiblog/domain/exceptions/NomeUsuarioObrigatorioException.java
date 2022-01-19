package com.paulojunior97.apiblog.domain.exceptions;

public class NomeUsuarioObrigatorioException extends ValidacaoException {

    public NomeUsuarioObrigatorioException() {
        super("Nome do usuário é um parâmetro obrigatório.", 400);
    }

}
