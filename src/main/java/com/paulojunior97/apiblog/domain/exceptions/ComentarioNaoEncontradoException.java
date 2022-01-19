package com.paulojunior97.apiblog.domain.exceptions;

public class ComentarioNaoEncontradoException extends ValidacaoException {

    public ComentarioNaoEncontradoException(long id) {
        super(String.format("Não foi encontrado o comentário com o id '%s'.", id), 404);
    }
}
