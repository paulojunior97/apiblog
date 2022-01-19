package com.paulojunior97.apiblog.api.dto;

import com.paulojunior97.apiblog.domain.entity.PerfilEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel("Usuário")
@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto {

    @ApiModelProperty(required = true)
    public String nome;

    @ApiModelProperty(required = true)
    public String email;

    @ApiModelProperty(required = true)
    public String senha;

    @ApiModelProperty(required = true)
    public PerfilEnum perfil;

    public UsuarioDto(String nome, String email, String senha, PerfilEnum perfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }
}
