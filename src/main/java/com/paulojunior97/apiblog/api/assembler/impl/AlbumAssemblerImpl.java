package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.AlbumDto;
import com.paulojunior97.apiblog.domain.entity.Album;
import com.paulojunior97.apiblog.domain.entity.Foto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Qualifier("album")
@Service
public class AlbumAssemblerImpl implements Assembler<AlbumDto, Album> {

    @Override
    public Album dtoToEntity(AlbumDto dto) {
        Album album = new Album(dto.titulo, dto.descricao);
        String titulo = StringUtils.isBlank(dto.getTitulo()) ? "Álbum" : dto.getTitulo();
        album.setTitulo(titulo);
        album.setDataPublicacao(LocalDateTime.now());
        album.setFotos(getListFotos(dto.fotosBase64, album));
        return album;
    }

    @Override
    public AlbumDto entityToDto(Album entity) {
        AlbumDto albumDto = new AlbumDto(entity.getTitulo(), entity.getDescricao());
        albumDto.fotosBase64 = getListFotosDto(entity.getFotos());
        return albumDto;
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
