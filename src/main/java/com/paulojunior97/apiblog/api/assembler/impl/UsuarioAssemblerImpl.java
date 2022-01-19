package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.UsuarioDto;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("usuario")
@Service
public class UsuarioAssemblerImpl implements Assembler<UsuarioDto, Usuario> {

    @Override
    public Usuario dtoToEntity(UsuarioDto dto) {
        return new Usuario(dto.nome, dto.email, dto.senha, dto.perfil);
    }

    @Override
    public UsuarioDto entityToDto(Usuario entity) {
        return new UsuarioDto(entity.getNome(), entity.getEmail(), entity.getSenha(), entity.getPerfil());
    }
}
