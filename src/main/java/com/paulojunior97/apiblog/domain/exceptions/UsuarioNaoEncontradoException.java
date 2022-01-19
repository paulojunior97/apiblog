package com.paulojunior97.apiblog.domain.exceptions;

public class UsuarioNaoEncontradoException extends ValidacaoException {

    public UsuarioNaoEncontradoException(long id) {
        super(String.format("Não foi encontrado um usuário com o id '%s'.", id), 404);
    }

}
