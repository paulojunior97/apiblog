package com.paulojunior97.apiblog.domain.service;

import com.paulojunior97.apiblog.domain.entity.Comentario;

import java.util.List;

public interface ComentarioService {
    Comentario buscarPorId(Long id);
    Comentario cadastrar(Comentario comentario, String emailAutor);
    List<Comentario> buscarTodosComentariosDeUmPost(Long idPost);
    void excluir(Long id, String emailUsuario);
    void excluirComentariosDoPost(Long idPost);
}
