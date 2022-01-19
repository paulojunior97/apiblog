package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.ComentarioResponseDto;
import com.paulojunior97.apiblog.domain.entity.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Qualifier("comentarioList")
@Service
public class ListComentarioAssemblerImpl implements Assembler<List<ComentarioResponseDto>, List<Comentario>> {

    @Autowired
    private ComentarioAssemblerImpl assembler;

    @Override
    public List<Comentario> dtoToEntity(List<ComentarioResponseDto> dto) {
        if(dto != null) {
            return dto.stream().map(assembler::dtoToEntity).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<ComentarioResponseDto> entityToDto(List<Comentario> entity) {
        if(entity != null){
            return entity.stream().map(assembler::entityToDto).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public void setAssembler(ComentarioAssemblerImpl assembler) {
        this.assembler = assembler;
    }

}
