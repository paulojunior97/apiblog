package com.paulojunior97.apiblog.api.dto;

import com.paulojunior97.apiblog.domain.entity.PerfilEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponseDto extends UsuarioDto {

    private Long id;

    public UsuarioResponseDto(String nome, String email, String senha, PerfilEnum perfil, Long id) {
        super(nome, email, senha, perfil);
        this.id = id;
    }
}
