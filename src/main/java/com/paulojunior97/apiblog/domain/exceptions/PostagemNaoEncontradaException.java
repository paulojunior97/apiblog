package com.paulojunior97.apiblog.domain.exceptions;

public class PostagemNaoEncontradaException extends ValidacaoException {

    public PostagemNaoEncontradaException(long id) {
        super(String.format("Não foi encontrado a postagem com o id '%s'.", id), 404);
    }

}
