package com.paulojunior97.apiblog.domain.service;

import com.paulojunior97.apiblog.domain.entity.Album;

import java.util.List;

public interface AlbumService {
    Album cadastrar(String emailCriador, Album album);
    Album buscarPeloId(Long id);
    void excluir(Long id, String emailUsuario);
    List<Album> listar();
}
