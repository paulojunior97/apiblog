package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.dto.ComentarioResponseDto;
import com.paulojunior97.apiblog.domain.entity.Comentario;
import com.paulojunior97.apiblog.domain.entity.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ListComentarioAssemblerImplTeste {

    private ListComentarioAssemblerImpl assembler;

    @Before
    public void before(){
        assembler = new ListComentarioAssemblerImpl();
        assembler.setAssembler(new ComentarioAssemblerImpl());
    }

    @Test
    public void listComentarioDtoParaEntity(){
        //cenario
        List<ComentarioResponseDto> comentariosDto = Arrays.asList(
                new ComentarioResponseDto("testeComentario1", 84751l),
                new ComentarioResponseDto("testeComentario2", 84752l),
                new ComentarioResponseDto("testeComentario3", 84758l));

        //acao
        List<Comentario> comentarios = assembler.dtoToEntity(comentariosDto);

        //verificacao
        Assert.assertEquals(comentariosDto.size(), comentarios.size());
    }

    @Test
    public void listComentarioEntityParaDto(){
        //cenario
        Comentario comentario1 = new Comentario("testeComentario1");
        comentario1.setPost(new Post());
        Comentario comentario2 = new Comentario("testeComentario2");
        comentario2.setPost(new Post());
        Comentario comentario3 = new Comentario("testeComentario3");
        comentario3.setPost(new Post());

        List<Comentario> comentarios = Arrays.asList(comentario1,comentario2,comentario3);

        //acao
        List<ComentarioResponseDto> comentariosDto = assembler.entityToDto(comentarios);

        //verificacao
        Assert.assertEquals(comentarios.size(), comentariosDto.size());
    }

    @Test
    public void listComentarioDtoNull(){
        //cenario

        //acao
        List<Comentario> comentarios = assembler.dtoToEntity(null);

        //verificacao
        Assert.assertEquals(0, comentarios.size());
    }

    @Test
    public void listComentarioEntityNull(){
        //cenario

        //acao
        List<ComentarioResponseDto> comentariosDto = assembler.entityToDto(null);

        //verificacao
        Assert.assertEquals(0, comentariosDto.size());
    }
}
