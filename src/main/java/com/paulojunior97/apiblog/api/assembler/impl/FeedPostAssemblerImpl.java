package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.ComentarioResponseDto;
import com.paulojunior97.apiblog.api.dto.FeedNoticiaDto;
import com.paulojunior97.apiblog.api.dto.LinkDto;
import com.paulojunior97.apiblog.api.dto.UsuarioResponseDto;
import com.paulojunior97.apiblog.domain.entity.Imagem;
import com.paulojunior97.apiblog.domain.entity.Link;
import com.paulojunior97.apiblog.domain.entity.Post;
import com.paulojunior97.apiblog.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Qualifier("feedPost")
@Service
public class FeedPostAssemblerImpl implements Assembler<FeedNoticiaDto, Post> {

    @Autowired
    @Qualifier("usuarioResponse")
    private Assembler usuarioAssembler;

    @Autowired
    @Qualifier("comentarioList")
    private Assembler listComentarioAssembler;

    @Override
    public Post dtoToEntity(FeedNoticiaDto dto) {
        Post post = new Post(dto.titulo, dto.descricao);
        post.setImagens(getListImagens(dto.imagensBase64, post));
        post.setLinks(getListLinks(dto.links, post));
        post.setCriador((Usuario) usuarioAssembler.dtoToEntity(dto.getCriador()));
        return post;
    }

    @Override
    public FeedNoticiaDto entityToDto(Post entity) {
        FeedNoticiaDto feedNoticiaDto = new FeedNoticiaDto(entity.getTitulo(), entity.getConteudo());
        if (entity.getDataPublicacao() != null) {
            feedNoticiaDto.setDataPublicacao(Date.from(entity.getDataPublicacao().atZone(ZoneId.systemDefault()).toInstant()));
        }
        feedNoticiaDto.setId(entity.getId());
        feedNoticiaDto.comentarios = (entity.getComentarios() != null ?  (List<ComentarioResponseDto>) listComentarioAssembler.entityToDto(entity.getComentarios()) : new ArrayList<>());
        feedNoticiaDto.setCriador((UsuarioResponseDto) usuarioAssembler.entityToDto(entity.getCriador()));
        feedNoticiaDto.links = getListLinksDto(entity.getLinks());
        feedNoticiaDto.imagensBase64 = getListImagensDto(entity.getImagens());
        return feedNoticiaDto;
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
