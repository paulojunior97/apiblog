package com.paulojunior97.apiblog.api.handler;

import com.paulojunior97.apiblog.api.dto.DetalheErro;
import com.paulojunior97.apiblog.domain.exceptions.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<DetalheErro> handleValidacaoException
            (ValidacaoException e, HttpServletRequest request) {

        DetalheErro erro = new DetalheErro(e.getMessage(), e.getStatus());

        return ResponseEntity.status(e.getStatus()).body(erro);
    }
}
