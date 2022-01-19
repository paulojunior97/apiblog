package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.ComentarioResponseDto;
import com.paulojunior97.apiblog.domain.entity.Comentario;
import com.paulojunior97.apiblog.domain.entity.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ComentarioAssemblerImplTeste {

    private Assembler assembler;

    @Before
    public void before(){
        assembler = new ComentarioAssemblerImpl();
    }

    @Test
    public void testeComentarioDtoParaEntity(){
        //cenario
        ComentarioResponseDto comentarioDto = new ComentarioResponseDto("teste", 96155l);

        //acao
        Comentario comentario = (Comentario) assembler.dtoToEntity(comentarioDto);

        //verificacao
        Assert.assertEquals(comentarioDto.conteudo, comentario.getComentario());
        Assert.assertEquals(comentarioDto.idPost, comentario.getPost().getId());
    }

    @Test
    public void testeComentarioEntityParaDto(){
        //cenario
        Comentario comentario = new Comentario("teste");
        comentario.setData(getDataAleatoria());
        Post post = new Post();
        post.setId(96155L);
        comentario.setPost(post);

        //acao
        ComentarioResponseDto comentarioDto = (ComentarioResponseDto) assembler.entityToDto(comentario);

        //verificacao
        Assert.assertEquals(comentario.getComentario(), comentarioDto.conteudo);
        Assert.assertEquals(comentario.getPost().getId(), comentarioDto.idPost);
        Assert.assertEquals(comentario.getData(), comentarioDto.data);
    }

    private Date getDataAleatoria(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -(new Random().nextInt(365)));
        return calendar.getTime();
    }
}
