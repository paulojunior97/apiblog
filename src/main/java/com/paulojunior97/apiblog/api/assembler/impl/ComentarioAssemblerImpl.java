package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.ComentarioDto;
import com.paulojunior97.apiblog.api.dto.ComentarioResponseDto;
import com.paulojunior97.apiblog.api.dto.UsuarioResponseDto;
import com.paulojunior97.apiblog.domain.entity.Comentario;
import com.paulojunior97.apiblog.domain.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("comentario")
@Service
public class ComentarioAssemblerImpl implements Assembler<ComentarioResponseDto, Comentario> {

    @Autowired
    @Qualifier("usuarioResponse")
    private Assembler usuarioAssembler;

    @Override
    public Comentario dtoToEntity(ComentarioResponseDto dto) {
        Comentario comentario = new Comentario(dto.conteudo);
        Post post = new Post();
        post.setId(dto.idPost);
        comentario.setPost(post);
        return comentario;
    }

    @Override
    public ComentarioResponseDto entityToDto(Comentario entity) {
        ComentarioResponseDto comentarioDto = new ComentarioResponseDto();
        comentarioDto.id = entity.getId();
        comentarioDto.conteudo = entity.getComentario();
        comentarioDto.idPost = entity.getPost().getId();
        comentarioDto.data = entity.getData();
        if (entity.getAutor() != null) {
            comentarioDto.autor = (UsuarioResponseDto) usuarioAssembler.entityToDto(entity.getAutor());
        }
        return comentarioDto;
    }

}
