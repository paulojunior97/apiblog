package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.UsuarioResponseDto;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Qualifier("usuarioResponseList")
@Service
public class ListUsuarioResponseAssemblerImpl implements Assembler<List<UsuarioResponseDto>, List<Usuario>> {

    @Autowired
    private UsuarioResponseAssemblerImpl assembler;

    @Override
    public List<Usuario> dtoToEntity(List<UsuarioResponseDto> dto) {
        return dto.stream().map(assembler::dtoToEntity).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponseDto> entityToDto(List<Usuario> entity) {
        return entity.stream().map(assembler::entityToDto).collect(Collectors.toList());
    }
}
