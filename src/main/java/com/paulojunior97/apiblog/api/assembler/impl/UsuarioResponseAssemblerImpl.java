package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.UsuarioResponseDto;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("usuarioResponse")
@Service
public class UsuarioResponseAssemblerImpl implements Assembler<UsuarioResponseDto, Usuario> {
    @Override
    public Usuario dtoToEntity(UsuarioResponseDto dto) {
        return new Usuario(dto.nome, dto.email, "", dto.perfil);
    }

    @Override
    public UsuarioResponseDto entityToDto(Usuario entity) {
        return new UsuarioResponseDto(entity.getNome(), entity.getEmail(), entity.getSenha(), entity.getPerfil(), entity.getId());
    }
}
