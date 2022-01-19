package com.paulojunior97.apiblog.domain.exceptions;

public class SemPermissaoExcluirAlbumException extends ValidacaoException {

    public SemPermissaoExcluirAlbumException() {
        super("Apenas o usuário que criou o álbum tem permissão para excluí-lo.", 400);
    }

}
