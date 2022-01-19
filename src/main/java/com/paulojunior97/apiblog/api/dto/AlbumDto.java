package com.paulojunior97.apiblog.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@ApiModel("Álbum")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {

    public String titulo;
    public String descricao;
    public List<String> fotosBase64;

    public AlbumDto(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
