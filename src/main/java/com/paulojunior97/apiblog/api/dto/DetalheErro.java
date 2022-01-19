package com.paulojunior97.apiblog.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetalheErro {

    private String mensagem;
    private int status;
    private long timestamp;

    public DetalheErro(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
    }
}
