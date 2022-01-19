package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.AlbumDto;
import com.paulojunior97.apiblog.domain.entity.Album;
import com.paulojunior97.apiblog.domain.entity.Foto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class AlbumAssemblerImplTeste {

    private Assembler assembler;

    @Before
    public void before(){
        assembler = new AlbumAssemblerImpl();
    }

    @Test
    public void testeAlbumDtoParaEntity(){
        //cenario
        AlbumDto albumDto = new AlbumDto("Teste Álbum 1", "Teste descricao do álbum");
        List<String> fotos = Arrays.asList("Flkclekwjcbçwdc","kcndkcnsdmek","lhdqçdhiqwieodnwkqljedlked");
        albumDto.fotosBase64 = fotos;

        //ação
        Album album = (Album) assembler.dtoToEntity(albumDto);

        //verificação
        Assert.assertEquals(albumDto.titulo, album.getTitulo());
        Assert.assertEquals(albumDto.descricao, album.getDescricao());
        Assert.assertEquals(albumDto.fotosBase64.size(), album.getFotos().size());
        for(int x = 0; x < albumDto.fotosBase64.size();x++){
            String fotoBase64 = "";
            try {
                fotoBase64 = new String(Base64.getDecoder().decode(new String(album.getFotos().get(x).getFoto()).getBytes("UTF-8")));
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Não foi possivel converter a foto");
            }
            Assert.assertEquals(albumDto.fotosBase64.get(x), fotoBase64);
        }
    }

    @Test
    public void testeAlbumEntityParaDto(){
        //cenario
        Album album = new Album("Teste Álbum entidade 1", "Teste Álbum descricao1");
        byte[] foto = Base64.getEncoder().encode("lkhflkwerjfnewkj".getBytes());
        byte[] foto2 = Base64.getEncoder().encode("klbcjwfdcbdkc".getBytes());
        List<Foto> fotos = Arrays.asList(new Foto(foto, album), new Foto(foto2, album));
        album.setFotos(fotos);

        //ação
        AlbumDto albumDto = (AlbumDto) assembler.entityToDto(album);

        //verificação
        Assert.assertEquals(album.getTitulo(), albumDto.titulo);
        Assert.assertEquals(album.getDescricao(), albumDto.descricao);
        Assert.assertEquals(album.getFotos().size(), albumDto.fotosBase64.size());
        for(int x = 0; x < album.getFotos().size();x++){
            String fotoBase64 = "";
            try {
                fotoBase64 = new String(Base64.getDecoder().decode(new String(album.getFotos().get(x).getFoto()).getBytes("UTF-8")));
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Não foi possivel converter a foto");
            }
            Assert.assertEquals(fotoBase64, albumDto.fotosBase64.get(x));
        }
    }

    @Test
    public void testeAlbumDtoParaEntityFotosNull(){
        //cenario
        AlbumDto albumDto = new AlbumDto("Teste Álbum 1", "Teste descricao álbum com fotos null");

        //ação
        Album album = (Album) assembler.dtoToEntity(albumDto);

        //verificação
        Assert.assertEquals(albumDto.titulo, album.getTitulo());
        Assert.assertEquals(albumDto.descricao, album.getDescricao());
        Assert.assertEquals(0, album.getFotos().size());
    }

    @Test
    public void testeAlbumEntityParaDtoFotosNull(){
        //cenario
        Album album = new Album("Teste Álbum 1", "Teste descricao DTO com fotos null");

        //ação
        AlbumDto albumDto = (AlbumDto) assembler.entityToDto(album);

        //verificação
        Assert.assertEquals(album.getTitulo(), albumDto.titulo);
        Assert.assertEquals(album.getDescricao(), albumDto.descricao);
        Assert.assertEquals(0, albumDto.fotosBase64.size());
    }
}
