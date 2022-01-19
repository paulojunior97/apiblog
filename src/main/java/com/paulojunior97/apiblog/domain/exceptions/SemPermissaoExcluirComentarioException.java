package com.paulojunior97.apiblog.domain.exceptions;

public class SemPermissaoExcluirComentarioException extends ValidacaoException {

    public SemPermissaoExcluirComentarioException() {
        super("Apenas o usuário que fez o comentário tem permissão para excluí-lo.", 400);
    }

}
