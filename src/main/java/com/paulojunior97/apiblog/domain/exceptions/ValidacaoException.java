package com.paulojunior97.apiblog.domain.exceptions;

public class ValidacaoException extends RuntimeException {

    private int status = 400;

    public ValidacaoException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
