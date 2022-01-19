package com.paulojunior97.apiblog.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@ApiModel("ComentárioResponse")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioResponseDto extends ComentarioDto {

    public Long id;

    @ApiModelProperty(required = true)
    public Date data;

    public UsuarioResponseDto autor;

    public ComentarioResponseDto(String conteudo, Long idPost) {
        super(conteudo, idPost);
    }
}
