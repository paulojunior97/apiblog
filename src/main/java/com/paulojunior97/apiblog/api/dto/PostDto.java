package com.paulojunior97.apiblog.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    public String titulo;
    public String conteudo;
    public List<LinkDto> links;
    public List<String> imagensBase64;

    public PostDto(String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
    }
}
