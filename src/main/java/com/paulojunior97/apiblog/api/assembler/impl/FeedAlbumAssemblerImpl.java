package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.FeedNoticiaDto;
import com.paulojunior97.apiblog.api.dto.UsuarioResponseDto;
import com.paulojunior97.apiblog.domain.entity.Album;
import com.paulojunior97.apiblog.domain.entity.Foto;
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

@Qualifier("feedAlbum")
@Service
public class FeedAlbumAssemblerImpl implements Assembler<FeedNoticiaDto, Album> {

    @Autowired
    @Qualifier("usuarioResponse")
    private Assembler usuarioAssembler;

    @Override
    public Album dtoToEntity(FeedNoticiaDto dto) {
        Album album = new Album(dto.titulo, dto.descricao);
        album.setFotos(getListFotos(dto.imagensBase64, album));
        album.setCriador((Usuario) usuarioAssembler.dtoToEntity(dto.getCriador()));
        return album;
    }

    @Override
    public FeedNoticiaDto entityToDto(Album entity) {
        FeedNoticiaDto feedNoticiaDto = new FeedNoticiaDto(entity.getTitulo(), entity.getDescricao());
        if (entity.getDataPublicacao() != null) {
            feedNoticiaDto.setDataPublicacao(Date.from(entity.getDataPublicacao().atZone(ZoneId.systemDefault()).toInstant()));
        }
        feedNoticiaDto.setId(entity.getId());
        feedNoticiaDto.setCriador((UsuarioResponseDto) usuarioAssembler.entityToDto(entity.getCriador()));
        feedNoticiaDto.imagensBase64 = getListFotosDto(entity.getFotos());
        return feedNoticiaDto;
    }

    private List<Foto> getListFotos(List<String> fotosDto, Album album){
        List<Foto> fotos = new ArrayList<>();
        if(fotosDto != null){
            for (String fotoDto : fotosDto) {
                byte[] foto = Base64.getEncoder().encode(fotoDto.getBytes());
                fotos.add(new Foto(foto, album));
            }
        }
        return fotos;
    }

    private List<String> getListFotosDto(List<Foto> fotos){
        List<String> fotosDto = new ArrayList<>();
        if(fotos != null) {
            for (Foto foto : fotos) {
                try {
                    fotosDto.add(new String(Base64.getDecoder().decode(new String(foto.getFoto()).getBytes("UTF-8"))));
                }  catch (UnsupportedEncodingException ex) {
                    System.out.println("Não foi possivel converter para string");
                }
            }
        }
        return fotosDto;
    }
}
