package com.paulojunior97.apiblog.domain.exceptions;

public class AlbumNaoEncontradoException extends ValidacaoException {

    public AlbumNaoEncontradoException(long id) {
        super(String.format("Não foi encontrado um álbum com o id '%s'.", id), 404);
    }

}
