package com.paulojunior97.apiblog.domain.service;

import com.paulojunior97.apiblog.domain.entity.Post;

import java.util.List;

public interface PostService {

    Post cadastrar(String emailCriador, Post post);
    Post buscarPorId(Long id);
    void excluir(String emailUsuario, Long idPostagem);
    List<Post> buscarTodos();
    List<Post> listar();
}
