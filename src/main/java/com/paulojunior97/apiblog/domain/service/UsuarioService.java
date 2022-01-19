package com.paulojunior97.apiblog.domain.service;

import com.paulojunior97.apiblog.domain.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario buscarPorEmail(String email);
    List<Usuario> buscarPorNomeEmail(String nome, String email);
    Usuario cadastrar(Usuario usuario);
    void deletar(Long id);
    void validar(Usuario usuario);
}
