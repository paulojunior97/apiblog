package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.LinkDto;
import com.paulojunior97.apiblog.api.dto.PostDto;
import com.paulojunior97.apiblog.domain.entity.Imagem;
import com.paulojunior97.apiblog.domain.entity.Link;
import com.paulojunior97.apiblog.domain.entity.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class PostAssemblerImplTeste {

    private Assembler assembler;

    @Before
    public void before(){
        assembler = new PostAssemblerImpl();
    }

    @Test
    public void testePostDtoParaEntity(){
        //cenario
        PostDto postDto = new PostDto("Teste", "Conteudo teste");
        postDto.links = Arrays.asList(new LinkDto("google.com", "Google"), new LinkDto("g1.com", "G1"));
        postDto.imagensBase64 = Arrays.asList("dahgjkbhvwejh","fasdkhfçkldjasg", "akfnjdajbb");

        //acao
        Post post = (Post) assembler.dtoToEntity(postDto);

        //verificacao
        Assert.assertEquals(postDto.conteudo, post.getConteudo());
        Assert.assertEquals(postDto.titulo, post.getTitulo());
        Assert.assertEquals(postDto.links.size(), post.getLinks().size());
        Assert.assertEquals(postDto.imagensBase64.size(), post.getImagens().size());
        for(int x = 0; x < postDto.links.size(); x++){
            Assert.assertEquals(postDto.links.get(x).getUrl(), post.getLinks().get(x).getUrl());
        }
        for(int x = 0; x < postDto.imagensBase64.size(); x++){
            String imagemBase64 = "";
            try {
                imagemBase64 = new String(Base64.getDecoder().decode(new String(post.getImagens().get(x).getImagem()).getBytes("UTF-8")));
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Não foi possivel converter a foto");
            }
            Assert.assertEquals(postDto.imagensBase64.get(x), imagemBase64);
        }
    }

    @Test
    public void testePostEntityParaDto(){
        //cenario
        Post post = new Post("Teste1", "Conteudo teste1");
        byte[] imagem = Base64.getEncoder().encode("djashfljkdah".getBytes());
        byte[] imagem2 = Base64.getEncoder().encode("fkjaslkdl".getBytes());
        List<Imagem> imagens = Arrays.asList(new Imagem(imagem, post), new Imagem(imagem2, post));
        post.setImagens(imagens);

        List<Link> links = Arrays.asList(new Link("google.com", "Google", post), new Link("g1.com", "G1", post), new Link("facebook.com", "Facebook", post));
        post.setLinks(links);

        //acao
        PostDto postDto = (PostDto) assembler.entityToDto(post);

        //verificacao
        Assert.assertEquals(post.getTitulo(), postDto.titulo);
        Assert.assertEquals(post.getConteudo(), postDto.conteudo);
        Assert.assertEquals(post.getImagens().size(), postDto.imagensBase64.size());
        Assert.assertEquals(post.getLinks().size(), postDto.links.size());
        for(int x = 0; x < post.getImagens().size(); x++){
            String imagemBase64 = "";
            try {
                imagemBase64 = new String(Base64.getDecoder().decode(new String(post.getImagens().get(x).getImagem()).getBytes("UTF-8")));
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Não foi possivel converter a foto");
            }
            Assert.assertEquals(imagemBase64, postDto.imagensBase64.get(x));
        }

        for(int x = 0; x < post.getLinks().size(); x++){
            Assert.assertEquals(post.getLinks().get(x).getUrl(), postDto.links.get(x).getUrl());
        }
    }

    @Test
    public void testePostDtoParaEntityLinksNullImagensNull(){
        //cenario
        PostDto postDto = new PostDto("Teste", "Conteudo teste");
        postDto.links = null;
        postDto.imagensBase64 = null;

        //acao
        Post post = (Post) assembler.dtoToEntity(postDto);

        //verificacao
        Assert.assertEquals(postDto.conteudo, post.getConteudo());
        Assert.assertEquals(postDto.titulo, post.getTitulo());
        Assert.assertEquals(0, post.getLinks().size());
        Assert.assertEquals(0, post.getImagens().size());
    }

    @Test
    public void testePostEntityParaDtoLinksNullImagensNull(){
        //cenario
        Post post = new Post("Teste1", "Conteudo teste1");
        post.setImagens(null);
        post.setLinks(null);

        //acao
        PostDto postDto = (PostDto) assembler.entityToDto(post);

        //verificacao
        Assert.assertEquals(post.getTitulo(), postDto.titulo);
        Assert.assertEquals(post.getConteudo(), postDto.conteudo);
        Assert.assertEquals(0, postDto.imagensBase64.size());
        Assert.assertEquals(0, postDto.links.size());
    }
}
