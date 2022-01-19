package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.LinkDto;
import com.paulojunior97.apiblog.api.dto.PostDto;
import com.paulojunior97.apiblog.domain.entity.Imagem;
import com.paulojunior97.apiblog.domain.entity.Link;
import com.paulojunior97.apiblog.domain.entity.Post;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Qualifier("post")
@Service
public class PostAssemblerImpl implements Assembler<PostDto, Post> {

    @Override
    public Post dtoToEntity(PostDto dto) {
        Post post = new Post(dto.titulo, dto.conteudo);
        post.setImagens(getListImagens(dto.imagensBase64, post));
        post.setLinks(getListLinks(dto.links, post));
        return post;
    }

    @Override
    public PostDto entityToDto(Post entity) {
        PostDto postDto = new PostDto(entity.getTitulo(), entity.getConteudo());
        postDto.links = getListLinksDto(entity.getLinks());
        postDto.imagensBase64 = getListImagensDto(entity.getImagens());
        return postDto;
    }

    private List<LinkDto> getListLinksDto(List<Link> links){
        List<LinkDto> linkDtos = new ArrayList<>();
        if(links != null) {
            for (Link link : links) {
                linkDtos.add(new LinkDto(link.getUrl(), link.getDescricao()));
            }
        }
        return linkDtos;
    }

    private List<String> getListImagensDto(List<Imagem> imagems) {
        List<String> imagensDto = new ArrayList<>();
        if(imagems != null) {
            for (Imagem imagem : imagems) {
                try {
                    imagensDto.add(new String(Base64.getDecoder().decode(new String(imagem.getImagem()).getBytes("UTF-8"))));
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("Não foi possivel converter para string");
                }
            }
        }
        return imagensDto;
    }

    private List<Imagem> getListImagens(List<String> imagensDto, Post post){
        List<Imagem> imagens = new ArrayList<>();
        if(imagensDto != null){
            for(String imagemDto : imagensDto){
                byte[] imagem = Base64.getEncoder().encode(imagemDto.getBytes());
                imagens.add(new Imagem(imagem, post));
            }
        }
        return imagens;
    }

    private List<Link> getListLinks(List<LinkDto> linksDto, Post post){
        List<Link> links = new ArrayList<>();
        if(linksDto != null) {
            for (LinkDto linkDto : linksDto) {
                links.add(new Link(linkDto.getUrl(), linkDto.getDescricao(), post));
            }
        }
        return links;
    }
}
