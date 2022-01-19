package com.paulojunior97.apiblog.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel("Comentário")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDto {

    @ApiModelProperty(required = true)
    public String conteudo;
    @ApiModelProperty(required = true)
    public Long idPost;

}
