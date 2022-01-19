package com.paulojunior97.apiblog.domain.exceptions;

public class SemPermissaoExcluirPostagemException extends ValidacaoException {

    public SemPermissaoExcluirPostagemException() {
        super("Apenas o usuário que criou a postagem tem permissão para excluí-la.", 400);
    }

}
