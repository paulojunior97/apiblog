package com.paulojunior97.apiblog.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FeedNoticiaDto {

    public Long id;
    public String titulo;
    public String descricao;
    public Date dataPublicacao;
    public List<LinkDto> links;
    public List<String> imagensBase64;
    public UsuarioResponseDto criador;
    public List<ComentarioResponseDto> comentarios = new ArrayList<>();

    public FeedNoticiaDto(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
