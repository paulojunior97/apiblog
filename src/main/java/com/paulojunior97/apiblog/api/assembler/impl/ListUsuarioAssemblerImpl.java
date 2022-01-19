package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.UsuarioDto;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Qualifier("usuarioList")
@Service
public class ListUsuarioAssemblerImpl implements Assembler<List<UsuarioDto>, List<Usuario>> {

    @Autowired
    private UsuarioAssemblerImpl assembler;

    @Override
    public List<Usuario> dtoToEntity(List<UsuarioDto> dto) {
        return dto.stream().map(assembler::dtoToEntity).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDto> entityToDto(List<Usuario> entity) {
        return entity.stream().map(assembler::entityToDto).collect(Collectors.toList());
    }

    public void setAssembler(UsuarioAssemblerImpl assembler) {
        this.assembler = assembler;
    }
}
